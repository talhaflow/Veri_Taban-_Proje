package com.talhakara.veri_taban_proje.ViewModel
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Connection
import java.sql.DriverManager



class KayitOlViewModel : ViewModel() {

    fun kaydetSQL(kullaniciAdi: String, password: String) {
        var connection: Connection? = null

        viewModelScope.launch(Dispatchers.IO) {
            try {
                connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "1905"
                )
                println("Bağlantı başarılı")

                val insertStatement = connection!!.prepareStatement(
                    "INSERT INTO dinleyici (dinleyici_ad, sifre) VALUES (?, ?)"
                )
                insertStatement.setString(1, kullaniciAdi)
                insertStatement.setString(2, password)
                insertStatement.executeUpdate()
                insertStatement.close()

                // Veritabanı işlemi başarılı, UI thread'inde işlem yapabilirsiniz
                withContext(Dispatchers.Main) {
                    // Kullanıcıya başarılı olduğunu bildirebilirsiniz
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("KayitOlViewModel", "Veritabanı hatası: ${e.message}", e)

                // Hatayı kullanıcıya bildirebilirsiniz
                withContext(Dispatchers.Main) {
                    // Toast veya Alert Dialog gösterebilirsiniz
                }
            } finally {
                connection?.close()
            }
        }
    }
}


