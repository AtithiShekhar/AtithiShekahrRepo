package com.example.myrecepieapp


// to have different routes
sealed class Screen(val route: String){
    object  RecipeScreen:Screen("recipescreen")
    object  DetailScreen:Screen("detailscreen")
}