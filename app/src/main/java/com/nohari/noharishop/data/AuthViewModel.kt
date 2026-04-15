package com.nohari.noharishop.data

import androidx.navigation.NavHostController
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.nohari.noharishop.models.User
import com.nohari.noharishop.navigation.ROUTE_DASHBOARD
import com.nohari.noharishop.navigation.ROUTE_LOGIN
import com.nohari.noharishop.navigation.ROUTE_REGISTER
import android.util.Log

class AuthViewModel(
    var navController: NavHostController,
    var context: Context
) {

    var mAuth = FirebaseAuth.getInstance()

    // ✅ STATE (OBSERVED BY COMPOSE)
    var username = mutableStateOf("")
        private set

    // ✅ FETCH USERNAME
    fun fetchUsername() {
        val user = mAuth.currentUser

        if (user != null) {
            val uid = user.uid
            val ref = FirebaseDatabase.getInstance()
                .getReference("Users")
                .child(uid)

            ref.get().addOnSuccessListener { snapshot ->
                val name = snapshot.child("fullname").value?.toString() ?: "User"
                username.value = name
            }.addOnFailureListener {
                Toast.makeText(context, "Failed to fetch username", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // ✅ SIGNUP
    fun signup(fullname: String, email: String, password: String, confirmation: String) {

        val trimmedEmail = email.trim()
        val trimmedPassword = password.trim()
        val trimmedFullname = fullname.trim()
        val trimmedConfirmation = confirmation.trim()

        val cleanEmail = trimmedEmail.replace("\n", "").replace("\r", "")
        val cleanPassword = trimmedPassword.replace("\n", "").replace("\r", "")

        if (cleanEmail.isBlank() || cleanPassword.isBlank() || trimmedConfirmation.isBlank()) {
            Toast.makeText(context, "Email and password cannot be blank", Toast.LENGTH_LONG).show()
            return
        }

        mAuth.createUserWithEmailAndPassword(cleanEmail, cleanPassword)
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {

                    val user = mAuth.currentUser

                    if (user != null) {
                        val uid = user.uid
                        val userdata = User(
                            fullname = trimmedFullname,
                            email = trimmedEmail,
                            userId = uid,
                            role = "User"
                        )

                        val regRef = FirebaseDatabase.getInstance()
                            .getReference("Users/")
                            .child(uid)

                        regRef.setValue(userdata).addOnCompleteListener { dbTask ->

                            if (dbTask.isSuccessful) {
                                Toast.makeText(context, "User Registered successfully", Toast.LENGTH_LONG).show()
                                navController.navigate(ROUTE_LOGIN)
                            } else {
                                Log.e("FIREBASE_DB_ERROR", dbTask.exception?.message ?: "Unknown DB error")
                                Toast.makeText(context, dbTask.exception?.message ?: "DB Error", Toast.LENGTH_LONG).show()
                            }
                        }
                    }

                } else {
                    val error = task.exception?.message ?: "Unknown signup error"
                    Log.e("FIREBASE_AUTH_ERROR", error)
                    Toast.makeText(context, "Signup failed: $error", Toast.LENGTH_LONG).show()
                    navController.navigate(ROUTE_REGISTER)
                }
            }
    }

    //
    fun login(email: String, password: String) {

        val trimmedEmail = email.trim()
        val trimmedPassword = password.trim()

        mAuth.signInWithEmailAndPassword(trimmedEmail, trimmedPassword)
            .addOnCompleteListener {

                if (it.isSuccessful) {
                    Toast.makeText(context, "User logged in successfully", Toast.LENGTH_LONG).show()

                    navController.navigate(ROUTE_DASHBOARD) {
                        popUpTo(ROUTE_LOGIN) { inclusive = true }
                    }

                } else {
                    val error = it.exception?.message ?: "Unknown login error"
                    Toast.makeText(context, "Login failed: $error", Toast.LENGTH_LONG).show()
                    Log.e("FIREBASE_LOGIN_ERROR", error)
                }
            }
    }

    //  LOGOUT
    fun logout() {
        mAuth.signOut()
        navController.navigate(ROUTE_LOGIN) {
            popUpTo(0)
        }
    }
}