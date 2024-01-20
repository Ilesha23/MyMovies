package com.example.mymovies

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.mymovies.ui.theme.MyMoviesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyMoviesTheme {
                MainScreen(modifier = Modifier)
            }
        }
    }

//    @Preview(
//        name = "Dark",
//        widthDp = 320,
//        heightDp = 400,
//        showBackground = true,
//        uiMode = Configuration.UI_MODE_NIGHT_YES
//    )
//    @Preview(
//        name = "Light",
//        widthDp = 320,
//        heightDp = 400,
//        showBackground = true,
//        uiMode = Configuration.UI_MODE_NIGHT_NO
//    )
//    @Composable
//    fun MainScreenPreview() {
//        MainScreen(modifier = Modifier)
//    }

    @Composable
    fun MainScreen(modifier: Modifier) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column {

            }
        }
    }

}