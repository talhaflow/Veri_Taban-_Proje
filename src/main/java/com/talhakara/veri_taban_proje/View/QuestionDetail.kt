package com.talhakara.veri_taban_proje.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun QuestionDetail(questionModel: QuestionModel,navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Soru başlığı
        Text(
            text = questionModel.question,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Seçenekler
        OptionItem("Option 1", questionModel.option1)
        OptionItem("Option 2", questionModel.option2)
        OptionItem("Option 3", questionModel.option3)
        OptionItem("Option 4", questionModel.option4)

        Spacer(modifier = Modifier.height(16.dp))

        // Doğru cevap
        Text(
            text = "Correct Answer: ${questionModel.answer}",
            style = MaterialTheme.typography.titleMedium,
            color = Color.Green,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Geri dön butonu
        Button(
            onClick = { /* Geri dön işlemi yapılabilir */ },
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
        ) {
            Text("Go Back", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
fun OptionItem(label: String, option: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Seçenek etiketi
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.width(80.dp)
        )

        // Seçenek metni
        Text(
            text = option,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.weight(1f)
        )
    }
}
