package com.talhakara.veri_taban_proje.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun AnaSayfa(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Compose Image or Icon for StartActivity
        Icon(
            imageVector = Icons.Default.PlayArrow,
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        navController.navigate("quiz")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary)
                ) {
                    Text("Start Quiz", style = MaterialTheme.typography.bodyLarge)
                }


        Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        val questionModel = QuestionModel(
                            "What is actually electricity?",
                            "A flow of water",
                            "A flow of air",
                            "A flow of electrons",
                            " A flow of atoms",
                            "A flow of electrons"
                        )
                        navController.navigate("questionDetail/${questionModel.question}")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary)
                ) {
                    Text("Question Detail", style = MaterialTheme.typography.bodyLarge)
                }

        Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    navController.navigate("result")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                Text("Show Result", style = MaterialTheme.typography.bodyLarge)
            }
    }
}



data class QuestionModel(
    val question: String,
    val option1: String,
    val option2: String,
    val option3: String,
    val option4: String,
    val answer: String
)
