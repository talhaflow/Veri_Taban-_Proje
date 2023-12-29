package com.talhakara.veri_taban_proje.View

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
@Composable
fun Quiz(navController: NavController) {
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var selectedOptionIndex by remember { mutableStateOf(-1) }
    val questions = listOf(
        "Soru 1: Bu bir test sorusudur.",
        "Soru 2: Başka bir test sorusu."
        // Diğer soruları buraya ekleyebilirsiniz.
    )

    val answers: MutableList<String> = mutableListOf()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        // Soru metni
        Text(
            text = questions[currentQuestionIndex],
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 16.dp).align(Alignment.CenterHorizontally)
        )

        // Şık seçenekleri
        Column {
            for ((index, option) in listOf("A", "B", "C", "D").withIndex()) {
                val isSelected = index == selectedOptionIndex
                val icon = if (isSelected) Icons.Default.Check else Icons.Default.FavoriteBorder

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            selectedOptionIndex = index
                        }
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = icon, contentDescription = null, modifier = Modifier.clickable {
                        answers.add(option)
                    })
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = " $option: Buraya şık metni gelecek.")
                }
            }

        }
        LazyColumn {
            items(answers) { answer ->
                Text(text = answer, style = MaterialTheme.typography.bodyMedium, color = Color.White)
            }
        }


        // İleri ve geri tuşları
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = {
                    if (currentQuestionIndex > 0) {
                        currentQuestionIndex--
                        selectedOptionIndex = -1
                    }
                }
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }

            IconButton(
                onClick = {
                    if (currentQuestionIndex < questions.size - 1 && selectedOptionIndex != -1) {
                        currentQuestionIndex++
                        selectedOptionIndex = -1
                    }
                }
            ) {
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
            }
        }
    }
}

@Composable
@Preview
fun QuizPreview() {
    Quiz(navController = NavController(LocalContext.current))
}
