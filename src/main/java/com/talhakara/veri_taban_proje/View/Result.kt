package com.talhakara.veri_taban_proje.View

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.talhakara.veri_taban_proje.Model.ArabeskSingleton
import com.talhakara.veri_taban_proje.Model.Cevap
import com.talhakara.veri_taban_proje.Model.CevapDeposu
import com.talhakara.veri_taban_proje.Model.MuzikTur
import com.talhakara.veri_taban_proje.Model.PopSingleton
import com.talhakara.veri_taban_proje.Model.RapSingleton
import com.talhakara.veri_taban_proje.Model.RockSingleton
import com.talhakara.veri_taban_proje.ViewModel.SarkiCekViewModel
import com.talhakara.veri_taban_proje.ViewModel.TestViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun Result() {
    var cevaplar by remember { mutableStateOf<MutableList<Cevap>>(mutableListOf()) }
    val viewModel: TestViewModel = viewModel()

    var arabeskler by remember { mutableStateOf<MutableList<MuzikTur>>(mutableListOf()) }
    var poplar by remember { mutableStateOf<MutableList<MuzikTur>>(mutableListOf()) }
    var Rapler by remember { mutableStateOf<MutableList<MuzikTur>>(mutableListOf()) }
    var rocklar by remember { mutableStateOf<MutableList<MuzikTur>>(mutableListOf()) }



    val SarkiCekViewModel : SarkiCekViewModel = viewModel()


    LaunchedEffect(true) {
        for (i in 1 until 11) {
            // Arka planda çalıştırılacak
            launch {
                viewModel.kullaniciSonCevabiCek(1881, i)
                // Belirli bir süre bekleyecek
                delay(1000)
                // Compose fonksiyonunu güncelleyecek
                cevaplar = CevapDeposu.cek().toMutableList()
            }
        }
        delay(1000)

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





    val siraliCevaplar = cevaplar.sortedBy { it.soruNo }



    val orneklist=puanHesapla(siraliCevaplar)


    
  /*  Column {
        if(orneklist==null){
            println("67")
        }else{
            for (orn in orneklist){
                Text(text = orn.toString())
            }
        }
    }*/

    PlaylistOlustur(liste = orneklist)



}


fun puanHesapla(cevaplar: List<Cevap>): MutableList<Int>? {
    // Null kontrolü
    if (cevaplar == null || cevaplar.size != 10) {
        return null
    }

    val puan: MutableList<Int> = mutableListOf()
    var rap_puan: Int=0
    var pop_puan: Int=0
    var rock_puan: Int=0
    var arabesk_puan: Int=0

    when (cevaplar.get(0).secilenCevap) {
        "A" -> pop_puan++
        "B" -> rap_puan++
        "C" -> rock_puan++
        "D" -> arabesk_puan++
    }

    when (cevaplar.get(1).secilenCevap) {
        "A" -> arabesk_puan++
        "B" -> rock_puan++
        "C" -> rap_puan++
        "D" -> pop_puan++
    }
    when (cevaplar.get(2).secilenCevap) {
        "A" -> pop_puan++
        "B" -> rock_puan++
        "C" -> rap_puan++
        "D" -> arabesk_puan++
    }
    when (cevaplar.get(3).secilenCevap) {
        "A" -> pop_puan++
        "B" -> rock_puan++
        "C" -> rap_puan++
        "D" -> arabesk_puan++
    }
    when (cevaplar.get(4).secilenCevap) {
        "A" -> arabesk_puan++
        "B" -> pop_puan++
        "C" -> rap_puan++
        "D" -> rock_puan++
    }
    when (cevaplar.get(5).secilenCevap) {
        "A" -> arabesk_puan++
        "B" -> pop_puan++
        "C" -> rap_puan++
        "D" -> rock_puan++
    }
    when (cevaplar.get(6).secilenCevap) {
        "A" -> arabesk_puan++
        "B" -> pop_puan++
        "C" -> rap_puan++
        "D" -> rock_puan++
    }
    when (cevaplar.get(7).secilenCevap) {
        "A" -> pop_puan++
        "B" -> rock_puan++
        "C" -> rap_puan++
        "D" -> arabesk_puan++
    }
    when (cevaplar.get(8).secilenCevap) {
        "A" -> rap_puan++
        "B" -> pop_puan++
        "C" -> arabesk_puan++
        "D" -> rock_puan++
    }
    when (cevaplar.get(9).secilenCevap) {
        "A" -> pop_puan++
        "B" -> arabesk_puan++
        "C" -> rap_puan++
        "D" -> rock_puan++
    }



    puan.add(rap_puan)
    puan.add(pop_puan)
    puan.add(rock_puan)
    puan.add(arabesk_puan)

    return puan
}

@Composable
fun PlaylistOlustur(liste: MutableList<Int>?) {
    if (liste == null) {
        println("liste boş")
    } else {
        var arabeskler by remember { mutableStateOf<MutableList<MuzikTur>>(mutableListOf()) }
        var poplar by remember { mutableStateOf<MutableList<MuzikTur>>(mutableListOf()) }
        var Rapler by remember { mutableStateOf<MutableList<MuzikTur>>(mutableListOf()) }
        var rocklar by remember { mutableStateOf<MutableList<MuzikTur>>(mutableListOf()) }

        val SarkiCekViewModel: SarkiCekViewModel = viewModel()

        LaunchedEffect(true) {
            repeat(10) { i ->
                launch {
                    SarkiCekViewModel.getPopListesi(i)
                    SarkiCekViewModel.getRapListesi(i)
                    SarkiCekViewModel.getRockListesi(i)
                    SarkiCekViewModel.getArabeskListesi(i)
                    delay(10000)
                    rocklar = RockSingleton.cek().toMutableList()
                    poplar = PopSingleton.cek().toMutableList()
                    arabeskler = ArabeskSingleton.cek().toMutableList()
                    Rapler = RapSingleton.cek().toMutableList()
                }
            }
        }

        LazyColumn {
            item {
                Text(text = "Arabesk Playlist")
                LazyRow {
                    items(minOf(liste.get(3), arabeskler.size)) { index ->
                        // Kart içinde gerekli şarkı bilgilerini göster
                        Card(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                           // elevation = 8.dp
                        ) {
                            Text(text = "arabesk" + arabeskler[index].sarki_ad)
                        }
                    }
                }
            }

            item {
                Text(text = "Pop Playlist")
                LazyRow {
                    items(minOf(liste.get(1), poplar.size)) { index ->
                        // Kart içinde gerekli şarkı bilgilerini göster
                        Card(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                           // elevation = 8.dp
                        ) {
                            Text(text = "pop" + poplar[index].sarki_ad)
                        }
                    }
                }
            }

            item {
                Text(text = "Rap Playlist")
                LazyRow {
                    items(minOf(liste.get(0), Rapler.size)) { index ->
                        // Kart içinde gerekli şarkı bilgilerini göster
                        Card(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                           // elevation = 8.dp
                        ) {
                            Text(text = "rap" + Rapler[index].sarki_ad)
                        }
                    }
                }
            }

            item {
                Text(text = "Rock Playlist")
                LazyRow {
                    items(minOf(liste.get(2), rocklar.size)) { index ->
                        // Kart içinde gerekli şarkı bilgilerini göster
                        Card(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                        ) {
                            Text(text = "rock" + rocklar[index].sarki_ad)
                        }
                    }
                }
            }
        }
    }
}
