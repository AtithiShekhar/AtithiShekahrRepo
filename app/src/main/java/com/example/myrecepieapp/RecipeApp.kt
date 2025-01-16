package com.example.myrecepieapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp(navController: NavHostController){
    val recipeViewModel:MainViewModel= viewModel()
    val viewstate by recipeViewModel.categoryState

    NavHost(navController = navController ,
        startDestination = Screen.RecipeScreen.route){
    composable(route = Screen.RecipeScreen.route){
        RecepieScreen(viewstate = viewstate, navigateToDetail =  {
        //this part is responsible for passing data from current screen
            //to detail screen
            navController.currentBackStackEntry?.savedStateHandle?.set("cat" ,it)
            navController.navigate(Screen.DetailScreen.route)
        })
    }
        composable(route = Screen.DetailScreen.route){
            val category = navController.previousBackStackEntry?.savedStateHandle?.get<Category>("cat")?:Category(""
                ,"" , " " , "")
            CategoryDetailScreen(category=category)
        }
    }
}