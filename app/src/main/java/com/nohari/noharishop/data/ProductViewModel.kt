package com.nohari.noharishop.data

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.nohari.noharishop.navigation.ROUTE_DASHBOARD
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.InputStream

class ProductViewModel(
    var navController: NavHostController,
    var context: Context
) {

    // Cloudinary config
    private val cloudinaryUrl =
        "https://api.cloudinary.com/v1_1/dkgilo0wp/image/upload"
    private val uploadPreset = "products"

    // Firebase
    private val databaseReference =
        FirebaseDatabase.getInstance().getReference("Products")

    // -------------------------------
    // Upload Product Function
    // -------------------------------
    fun uploadProduct(
        imageUri: Uri?,
        name: String,
        price: String,
        description: String
    ) {

        val ref = databaseReference.push()
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid ?: ""

        CoroutineScope(Dispatchers.IO).launch {
            try {

                // Upload image first
                val imageUrl = if (imageUri != null) {
                    uploadToCloudinary(imageUri)
                } else {
                    ""
                }

                val productData = mapOf(
                    "id" to ref.key,
                    "name" to name,
                    "price" to price,
                    "description" to description,
                    "userId" to userId,
                    "imageUrl" to imageUrl
                )

                // Save to Firebase
                ref.setValue(productData).addOnCompleteListener { task ->
                    CoroutineScope(Dispatchers.Main).launch {
                        if (task.isSuccessful) {
                            Toast.makeText(
                                context,
                                "Product saved successfully",
                                Toast.LENGTH_SHORT
                            ).show()

                            navController.navigate(ROUTE_DASHBOARD)

                        } else {
                            Toast.makeText(
                                context,
                                "Error: ${task.exception?.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

            } catch (e: Exception) {
                CoroutineScope(Dispatchers.Main).launch {
                    Toast.makeText(
                        context,
                        "Upload failed: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    // -------------------------------
    // Upload to Cloudinary
    // -------------------------------
    private fun uploadToCloudinary(uri: Uri): String {

        val inputStream: InputStream? =
            context.contentResolver.openInputStream(uri)

        val fileBytes = inputStream?.readBytes()
            ?: throw Exception("Image read failed")

        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart(
                "file",
                "image.jpg",
                fileBytes.toRequestBody("image/*".toMediaTypeOrNull())
            )
            .addFormDataPart("upload_preset", uploadPreset)
            .build()

        val request = Request.Builder()
            .url(cloudinaryUrl)
            .post(requestBody)
            .build()

        val response = OkHttpClient().newCall(request).execute()

        if (!response.isSuccessful) {
            throw Exception("Upload failed")
        }

        val responseBody = response.body?.string()

        val secureUrl = Regex("\"secure_url\":\"(.*?)\"")
            .find(responseBody ?: "")
            ?.groupValues?.get(1)

        return secureUrl
            ?: throw Exception("Failed to get image URL")
    }
}