package com.talhakara.veri_taban_proje.View

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.talhakara.veri_taban_proje.ui.theme.Veri_Tabanı_ProjeTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import java.sql.Connection
import java.sql.DriverManager


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Veri_Tabanı_ProjeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SayfaGecis()

                }
            }
        }
    }
}

@Composable
fun SayfaGecis() {
    var navController = rememberNavController()
    var QuestionModel=QuestionModel("","","","","","")

    NavHost(navController = navController, startDestination = "kayitSayfa") {


        composable("kayitSayfa") {
            kayitOl(navController = navController)
        }
        composable("loginSayfa") {
            loginSayfa(navController = navController)
        }


        composable("anaSayfa") {
            AnaSayfa(navController = navController)
        }

        composable("questionDetail") {
            QuestionDetail(questionModel=QuestionModel,navController = navController)
        }

        composable("result") {
            Result(navController = navController)
        }

        composable("quiz") {
            Quiz(navController = navController)
        }

        composable("admin") {
            AdminPanel(navController = navController)
        }


    }
}




