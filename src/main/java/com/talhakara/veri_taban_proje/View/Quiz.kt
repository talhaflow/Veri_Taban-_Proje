package com.talhakara.veri_taban_proje.View

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.talhakara.veri_taban_proje.Model.Cevap
import com.talhakara.veri_taban_proje.ViewModel.TestViewModel

@Composable
fun Quiz(navController: NavController,context: Context) {
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var selectedOptionIndex by remember { mutableStateOf(-1) }
    val TestViewModel: TestViewModel = viewModel()
    data class MuzikBeklentisi(
        val soru: String,
        val secenekA: String,
        val secenekB: String,
        val secenekC: String,
        val secenekD: String
    )

    var iconA by remember { mutableStateOf(Icons.Default.FavoriteBorder) }
    var iconB by remember { mutableStateOf(Icons.Default.FavoriteBorder) }
    var iconC by remember { mutableStateOf(Icons.Default.FavoriteBorder) }
    var iconD by remember { mutableStateOf(Icons.Default.FavoriteBorder) }



    val muzikBeklentisiListesi = listOf(
        MuzikBeklentisi(
            "Müzikten beklentin nedir?",
            "Huzurlu bir atmosfer yaratması",
            "Kulakta kalıcı bir melodi içermesi",
            "Çeşitli enstrümanların harmonisi",
            "Eğlenceli ve enerjik olması"
        ),
        MuzikBeklentisi(
            "Hangi tarz bir ses seni etkiler?",
            "Yumuşak ve sakin bir ses",
            "Güçlü ve çarpıcı bir ses",
            "Ritmik ve dinamik bir ses",
            "Neşeli ve keyifli bir ses"
        ),
        MuzikBeklentisi(
            "Bir müzik performansındaki enerji senin için ne kadar önemli?",
            "Çok önemli, sakin performansları tercih ederim",
            "Önemli, enerjik performanslar beni etkiler",
            "İkisi de benim için önemli",
            "Enerji seviyesi benim için önemsiz"
        ),
        MuzikBeklentisi(
            "Hangi türdeki melodiler seni daha çok etkiler?",
            "Yavaş ve duygusal melodiler",
            "Hızlı ve enerjik melodiler",
            "Çeşitli enstrümanların bir araya geldiği melodiler",
            "Basit ve akılda kalıcı melodiler"
        ),
        MuzikBeklentisi(
            "Bir şarkının atmosferi senin için ne kadar önemli?",
            "Çok önemli, atmosferik şarkıları tercih ederim",
            "Önemli, ama içindeki melodi daha önemli",
            "Atmosfer benim için önemsiz",
            "Melodi kadar önemli"
        ),
        MuzikBeklentisi(
            "Bir müzik parçasındaki duygusal yükseliş seni nasıl etkiler?",
            "Derinden etkiler, duygusal anlar önemlidir",
            "Etkiler, ancak melodi daha önemlidir",
            "Benim için pek önemli değil",
            "Duymam gerekmez, sadece ritmi önemlidir"
        ),
        MuzikBeklentisi(
            "Bir şarkının içindeki enstrümanların uyumu senin için ne ifade eder?",
            "Çok şey ifade eder, uyumlu enstrümanlar beni etkiler",
            "İyi bir şeydir, ancak vokal daha önemlidir",
            "Benim için önemli değil",
            "Fark etmez, sadece melodiye odaklanırım"
        ),
        MuzikBeklentisi(
            "Bir müziğin sana hissettirdiği duyguyu nasıl tanımlarsın?",
            "Huzurlu ve sakin",
            "Mutlu ve enerjik",
            "Heyecanlı ve coşkulu",
            "Neşeli ve keyifli"
        ),
        MuzikBeklentisi(
            "Müziğin senin ruh halini nasıl etkiler?",
            "Sakinleştirir ve rahatlatır",
            "Enerji verir ve mutlu eder",
            "Motive eder ve enerjik hissettirir",
            "Keyifli anlar yaşatır"
        ),
        MuzikBeklentisi(
            "Bir şarkının seni etkileyebilmesi için hangi özelliklere sahip olmalı?",
            "Güzel bir melodi",
            "Güçlü bir vokal performansı",
            "Çeşitli enstrümanların uyumu",
            "Enerjik ve eğlenceli bir ritim"
        )
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        // Soru metni
        Text(
            text = "${currentQuestionIndex+1}"+muzikBeklentisiListesi[currentQuestionIndex].soru,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 16.dp).align(Alignment.CenterHorizontally)
        )

        // Şık seçenekleri
        Column {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            selectedOptionIndex = 0
                            iconA = Icons.Default.Check
                            iconB = Icons.Default.FavoriteBorder
                            iconC = Icons.Default.FavoriteBorder
                            iconD = Icons.Default.FavoriteBorder

                            TestViewModel.cevapKaydet(Cevap(currentQuestionIndex+1,"A"),1881)
                        }
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = iconA,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = " A: ${muzikBeklentisiListesi.get(currentQuestionIndex).secenekA}")
                }


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            selectedOptionIndex = 1



                            TestViewModel.cevapKaydet(Cevap(currentQuestionIndex+1,"B"),1881)


                            iconB = Icons.Default.Check
                            iconA = Icons.Default.FavoriteBorder
                            iconC = Icons.Default.FavoriteBorder
                            iconD = Icons.Default.FavoriteBorder
                        }
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = iconB,
                        contentDescription = null,
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = " B: ${muzikBeklentisiListesi.get(currentQuestionIndex).secenekB}")
                }


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            selectedOptionIndex = 2

                            TestViewModel.cevapKaydet(Cevap(currentQuestionIndex+1,"C"),1881)



                            iconC=Icons.Default.Check
                            iconA=Icons.Default.FavoriteBorder
                            iconB=Icons.Default.FavoriteBorder
                            iconD=Icons.Default.FavoriteBorder
                        }
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = iconC,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = " C: ${muzikBeklentisiListesi.get(currentQuestionIndex).secenekC}")
                }


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            selectedOptionIndex = 3

                            TestViewModel.cevapKaydet(Cevap(currentQuestionIndex+1,"D"),1881)

                            iconD=Icons.Default.Check
                            iconB=Icons.Default.FavoriteBorder
                            iconA=Icons.Default.FavoriteBorder
                            iconC=Icons.Default.FavoriteBorder                        }
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = iconD,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = " D: ${muzikBeklentisiListesi.get(currentQuestionIndex).secenekD}")
                }




        }


        // ...

// İleri ve geri tuşları
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = {
                    iconA=Icons.Default.FavoriteBorder
                    iconB=Icons.Default.FavoriteBorder
                    iconC=Icons.Default.FavoriteBorder
                    iconD=Icons.Default.FavoriteBorder

                    if (currentQuestionIndex > 0) {
                        currentQuestionIndex--
                        selectedOptionIndex = -1
                    }
                }
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }


            Button(
                onClick = {

                          if(currentQuestionIndex ==9 && selectedOptionIndex != -1){

                              navController.navigate("result")

                          }else{
                              Toast.makeText(context, "SORULARI BİTİR", Toast.LENGTH_SHORT).show()
                          }
                    
                },
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            ) {
                Text("Testi Bitir")
            }

            IconButton(
                onClick = {

                    if (currentQuestionIndex < muzikBeklentisiListesi.size - 1 && selectedOptionIndex != -1) {
                            currentQuestionIndex++
                            selectedOptionIndex = -1
                        iconA=Icons.Default.FavoriteBorder
                        iconB=Icons.Default.FavoriteBorder
                        iconC=Icons.Default.FavoriteBorder
                        iconD=Icons.Default.FavoriteBorder
                    }
                }
            ) {
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
            }



        }
}


}

