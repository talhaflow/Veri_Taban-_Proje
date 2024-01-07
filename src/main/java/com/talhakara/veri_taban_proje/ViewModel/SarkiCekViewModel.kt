package com.talhakara.veri_taban_proje.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.talhakara.veri_taban_proje.Model.ArabeskSingleton
import com.talhakara.veri_taban_proje.Model.Cevap
import com.talhakara.veri_taban_proje.Model.CevapDeposu
import com.talhakara.veri_taban_proje.Model.MuzikTur
import com.talhakara.veri_taban_proje.Model.PopSingleton
import com.talhakara.veri_taban_proje.Model.RapSingleton
import com.talhakara.veri_taban_proje.Model.RockSingleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet

class SarkiCekViewModel : ViewModel() {

    fun getRockListesi(sarkiId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val jdbcUrl = "jdbc:postgresql://192.168.43.185:5432/postgres"
            val dbUser = "postgres"
            val dbpassword = "1905"
            var Muzik: MuzikTur? = null

            try {
                DriverManager.getConnection(jdbcUrl, dbUser, dbpassword).use { connection ->
                    println("Bağlantı başarılı rock")

                    val sql = "SELECT * FROM public.rock2 WHERE sarki_id = ?"
                    val preparedStatement: PreparedStatement = connection.prepareStatement(sql)
                    preparedStatement.setInt(1, sarkiId)
                    val resultSet: ResultSet = preparedStatement.executeQuery()

                    while (resultSet.next()) {
                        Muzik = MuzikTur(
                            tur = "rock",
                            sarki_id = resultSet.getInt("sarki_id"),
                            sanatci_id = resultSet.getInt("sanatci_id"),
                            sarki_ad = resultSet.getString("sarki_adi")
                        )
                    }
                }

                // Singleton üzerinden LiveData'yi güncelleme
                withContext(Dispatchers.Main) {
                    if (Muzik != null) {
                        RockSingleton.ekle(Muzik!!)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()

                // Hata durumunda UI thread'inde işlem yapabilirsiniz
                withContext(Dispatchers.Main) {
                    println("rock"+e.message)
                }
            }
        }
    }


    fun getRapListesi(sarkiId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            val jdbcUrl = "jdbc:postgresql://192.168.43.185:5432/postgres"
            val dbUser = "postgres"
            val dbpassword = "1905"
            var Muzik: MuzikTur? = null

            try {
                DriverManager.getConnection(jdbcUrl, dbUser, dbpassword).use { connection ->
                    println("Bağlantı başarılı rap")

                    val sql = "SELECT * FROM public.rap2 WHERE sarki_id = ?"
                    val preparedStatement: PreparedStatement = connection.prepareStatement(sql)
                    preparedStatement.setInt(1, sarkiId)
                    val resultSet: ResultSet = preparedStatement.executeQuery()

                    while (resultSet.next()) {
                        Muzik = MuzikTur(
                            tur = "Rap",
                            sarki_id = resultSet.getInt("sarki_id"),
                            sanatci_id = resultSet.getInt("sanatci_id"),
                            sarki_ad = resultSet.getString("sarki_adi")
                        )
                    }
                }

                // Singleton üzerinden LiveData'yi güncelleme
                withContext(Dispatchers.Main) {
                    if (Muzik != null) {
                        RapSingleton.ekle(Muzik!!)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()

                // Hata durumunda UI thread'inde işlem yapabilirsiniz
                withContext(Dispatchers.Main) {
                    println("rap" + e.message)
                }
            }
        }


    }

    fun getPopListesi(sarkiId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            val jdbcUrl = "jdbc:postgresql://192.168.43.185:5432/postgres"
            val dbUser = "postgres"
            val dbpassword = "1905"
            var Muzik: MuzikTur? = null

            try {
                DriverManager.getConnection(jdbcUrl, dbUser, dbpassword).use { connection ->
                    println("Bağlantı başarılı pop")

                    val sql = "SELECT * FROM public.pop2 WHERE sarki_id = ?"
                    val preparedStatement: PreparedStatement = connection.prepareStatement(sql)
                    preparedStatement.setInt(1, sarkiId)
                    val resultSet: ResultSet = preparedStatement.executeQuery()

                    while (resultSet.next()) {
                        Muzik = MuzikTur(
                            tur = "pop",
                            sarki_id = resultSet.getInt("sarki_id"),
                            sanatci_id = resultSet.getInt("sanatci_id"),
                            sarki_ad = resultSet.getString("sarki_adi")
                        )
                    }
                }

                // Singleton üzerinden LiveData'yi güncelleme
                withContext(Dispatchers.Main) {
                    if (Muzik != null) {
                        PopSingleton.ekle(Muzik!!)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()

                // Hata durumunda UI thread'inde işlem yapabilirsiniz
                withContext(Dispatchers.Main) {
                    println(e.message)
                }
            }
        }


    }


    fun getArabeskListesi(sarkiId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val jdbcUrl = "jdbc:postgresql://192.168.43.185:5432/postgres"
            val dbUser = "postgres"
            val dbpassword = "1905"
            var arabesk: MuzikTur? = null

            try {
                DriverManager.getConnection(jdbcUrl, dbUser, dbpassword).use { connection ->
                    println("Bağlantı başarılı arabesk")

                    val sql = "SELECT * FROM public.arabesk2 WHERE sarki_id = ?"
                    val preparedStatement: PreparedStatement = connection.prepareStatement(sql)
                    preparedStatement.setInt(1, sarkiId)
                    val resultSet: ResultSet = preparedStatement.executeQuery()

                    while (resultSet.next()) {
                        arabesk = MuzikTur(
                            tur = "arabesk",
                            sarki_id = resultSet.getInt("sarki_id"),
                            sanatci_id = resultSet.getInt("sanatci_id"),
                            sarki_ad = resultSet.getString("sarki_adi")
                        )
                    }
                }

                // Singleton üzerinden LiveData'yi güncelleme
                withContext(Dispatchers.Main) {
                    if (arabesk != null) {
                        ArabeskSingleton.ekle(arabesk!!)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()

                // Hata durumunda UI thread'inde işlem yapabilirsiniz
                withContext(Dispatchers.Main) {
                    println(e.message)
                }
            }
        }

    }





}
