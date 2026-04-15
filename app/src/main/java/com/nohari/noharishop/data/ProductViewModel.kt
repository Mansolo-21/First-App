package com.nohari.noharishop.data

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

class ProductViewModel(
    var navController: NavHostController,
    var context: Context
) {

    private val TAG = "PRODUCT_DEBUG"

    private val databaseReference =
        FirebaseDatabase.getInstance().getReference("Products")

    // 🔥 CHANGE THESE
    private val CLOUD_NAME = "dkgilo0wp"
    private val UPLOAD_PRESET = "nohari_upload"

    fun uploadProduct(
        imageUri: Uri?,
        name: String,
        price: String,
        description: String
    ) {

        if (imageUri == null) {
            Toast.makeText(context, "Select an image", Toast.LENGTH_SHORT).show()
            return
        }

        val ref = databaseReference.push()
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""

        CoroutineScope(Dispatchers.IO).launch {

            try {

                // 1. Upload image to Cloudinary
                val imageUrl = uploadToCloudinary(imageUri)

                if (imageUrl == null) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "Image upload failed", Toast.LENGTH_LONG).show()
                    }
                    return@launch
                }

                // 2. Save product to Firebase
                val productData = mapOf(
                    "id" to ref.key,
                    "name" to name,
                    "price" to price,
                    "description" to description,
                    "userId" to userId,
                    "imageUrl" to imageUrl
                )

                ref.setValue(productData).addOnCompleteListener { task ->

                    CoroutineScope(Dispatchers.Main).launch {

                        if (task.isSuccessful) {
                            Toast.makeText(context, "Product uploaded", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, task.exception?.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }

            } catch (e: Exception) {

                withContext(Dispatchers.Main) {
                    Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    // 🔥 CLOUDINARY UPLOAD FUNCTION
    private fun uploadToCloudinary(uri: Uri): String? {

        return try {

            val url = URL("https://api.cloudinary.com/v1_1/$CLOUD_NAME/image/upload")
            val connection = url.openConnection() as HttpURLConnection

            connection.requestMethod = "POST"
            connection.doOutput = true

            val outputStream = connection.outputStream
            val writer = OutputStreamWriter(outputStream)

            val imageBytes = context.contentResolver.openInputStream(uri)?.readBytes()

            val base64Image = android.util.Base64.encodeToString(imageBytes, android.util.Base64.NO_WRAP)

            val body =
                "file=data:image/jpeg;base64,$base64Image&upload_preset=$UPLOAD_PRESET"

            writer.write(body)
            writer.flush()
            writer.close()

            val response = connection.inputStream.bufferedReader().readText()

            val json = JSONObject(response)
            json.getString("secure_url")

        } catch (e: Exception) {
            Log.e(TAG, "Cloudinary error: ${e.message}")
            null
        }
    }
}