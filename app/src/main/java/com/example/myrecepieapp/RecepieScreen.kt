package com.example.myrecepieapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
//import androidx.compose.material3
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import org.w3c.dom.Text

@Composable
fun RecepieScreen(modifier: Modifier=Modifier ,
                  viewstate:MainViewModel.RecipeState,
                  navigateToDetail : (Category) -> Unit) {


    val recepieViewModel: MainViewModel = viewModel()

    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = " The Recepie App",
            color = Color.Magenta,
            style = TextStyle(fontWeight = FontWeight.W900)  ,
            modifier= Modifier.padding(top = 20.dp).systemBarsPadding().align(Alignment.TopCenter))
        when {
            viewstate.loading -> {
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }

            viewstate.error != null -> {
                Text("Error Occurred")
            }

            else -> {
                CategoryScreen(categories = viewstate.list , navigateToDetail )
            }
        }
    }
}
@Composable
fun CategoryScreen(categories: List<Category>,
                   navigateToDetail : (Category) -> Unit){
    LazyVerticalGrid(GridCells.Fixed(2) ,  modifier =Modifier.fillMaxSize()) {
    items(categories){
        category ->
        CategoryItem(category=category , navigateToDetail )
    }
    }


}
// how each item looks like
@Composable
fun  CategoryItem(category: Category ,
                  navigateToDetail : (Category) -> Unit ){
    Column (modifier = Modifier.padding(16.dp)
        .fillMaxSize().clickable { navigateToDetail(category) },
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Image(
            painter= rememberAsyncImagePainter(category.strCategoryThumb),
            contentDescription = null ,
            modifier = Modifier.fillMaxSize().aspectRatio(1f)
        )


        Text(
            text = category.strCategory,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold)  ,
            modifier= Modifier.padding(top = 12.dp)
        )

    }
}