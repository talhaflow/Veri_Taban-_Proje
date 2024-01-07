package com.talhakara.veri_taban_proje.View

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.talhakara.veri_taban_proje.Model.ArabeskSingleton
import com.talhakara.veri_taban_proje.Model.MuzikTur
import com.talhakara.veri_taban_proje.Model.PopSingleton
import com.talhakara.veri_taban_proje.Model.RapSingleton
import com.talhakara.veri_taban_proje.Model.RockSingleton
import com.talhakara.veri_taban_proje.ViewModel.SarkiCekViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Calisma() {
    var arabeskler by remember { mutableStateOf<MutableList<MuzikTur>>(mutableListOf()) }
    var poplar by remember { mutableStateOf<MutableList<MuzikTur>>(mutableListOf()) }
    var Rapler by remember { mutableStateOf<MutableList<MuzikTur>>(mutableListOf()) }
    var rocklar by remember { mutableStateOf<MutableList<MuzikTur>>(mutableListOf()) }



    val SarkiCekViewModel : SarkiCekViewModel = viewModel()


    LaunchedEffect(true) {
        for (i in 1 until 10) {
            // Arka planda çalıştırılacak
            launch {
                    SarkiCekViewModel.getPopListesi(i)
                    SarkiCekViewModel.getRapListesi(i)
                    SarkiCekViewModel.getRockListesi(i)
                    SarkiCekViewModel.getArabeskListesi(i)

                // Belirli bir süre bekleyecek
                delay(10000)

                rocklar = RockSingleton.cek().toMutableList()
                poplar = PopSingleton.cek().toMutableList()
                arabeskler = ArabeskSingleton.cek().toMutableList()
                Rapler = RapSingleton.cek().toMutableList()


            }
        }

    }

    Column {
        // Pop verilerinden rastgele 10 şarkı
        repeat(minOf(2, arabeskler.size)) {
            Text(text = "arabesk"+arabeskler.random().sarki_ad)
        }

        // Pop verilerinden rastgele 10 şarkı
        repeat(minOf(2, poplar.size)) {
            Text(text = "pop"+poplar.random().sarki_ad)
        }

        // Rap verilerinden rastgele 10 şarkı
        repeat(minOf(2, Rapler.size)) {
            Text(text = "rap"+Rapler.random().sarki_ad)
        }

        // Rock verilerinden rastgele 10 şarkı
        repeat(minOf(2, rocklar.size)) {
            Text(text ="rock"+ rocklar.random().sarki_ad)
        }
    }





}




