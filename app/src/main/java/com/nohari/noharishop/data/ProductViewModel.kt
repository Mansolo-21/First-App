package com.nohari.noharishop.data

import androidx.navigation.NavHostController
import com.google.api.Context

class ProductViewModel(var navController: NavHostController,var content: Context){
    var cloudinaryUrl ="https://api.cloudinary.com/v1_1/dkgilo0wp/image/upload"
    val uploadPreset="products"

    //upload product
    //update product
    //delete product
    //fetch product
}