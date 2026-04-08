package com.nohari.noharishop.data

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import android.content.Context
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.nohari.noharishop.models.User
import com.nohari.noharishop.navigation.ROUTE_LOGIN
import com.nohari.noharishop.navigation.ROUTE_REGISTER

class AuthViewModel(var navController: NavHostController,var context:Context){
    var mAuth= FirebaseAuth.getInstance()
    //signup function
    fun signup(fullname: String,email: String,password:String,confirmation: String){
        //validation
        if (email.isBlank() || password.isBlank() || confirmation.isBlank()){
            Toast.makeText(context,"Email and password cannot be blank",Toast.LENGTH_LONG).show()
            return
        }else if(password != confirmation){
            Toast.makeText(context,"Passwords don't match",Toast.LENGTH_LONG).show()
        }else{
            mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener {
                    if(it.isSuccessful){
                        val userdata= User(fullname, email, password,mAuth.currentUser !!.uid)
                        val regRef= FirebaseDatabase.getInstance().getReference()
                            .child("Users"+mAuth.currentUser !!.uid)
                        regRef.setValue(userdata).addOnCompleteListener {
                            if(it.isSuccessful){
                                Toast.makeText(context,"User Registered successfully",Toast.LENGTH_LONG).show()
                                navController.navigate(ROUTE_LOGIN)
                            }else{
                                Toast.makeText(context,"${it.exception!!.message}",Toast.LENGTH_LONG).show()
                                navController.navigate(ROUTE_LOGIN)
                            }
                        }
                    }
                    else{
                        navController.navigate(ROUTE_REGISTER) }
                }
        }

    }

    //login function
}