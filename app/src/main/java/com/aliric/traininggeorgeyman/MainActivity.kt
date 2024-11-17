package com.aliric.traininggeorgeyman

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aliric.traininggeorgeyman.ui.theme.TrainingGeorgeyManTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val calculatorViewModel=ViewModelProvider(this)[CalculatorViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            TrainingGeorgeyManTheme {
                MyNavigation(calculatorViewModel)


                }
            }
        }
    }

@Composable
fun MyNavigation(calculatorViewModel: CalculatorViewModel){



    val navController= rememberNavController()
    
    NavHost(navController = navController, startDestination = "FirstPage") {

        composable(route = "FirstPage"){
            Calculator(navController,calculatorViewModel)

        }
        composable(route = "SecondPage"){
            Converter(navController)

        }

    }

}
