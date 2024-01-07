package com.talhakara.veri_taban_proje.ViewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Connection
import java.sql.DriverManager

class GirisYapViewModel : ViewModel() {

    fun girisSQL(kullaniciAdi: String, password: String,navController: NavController,context: Context) {
        var connection: Connection? = null

        viewModelScope.launch(Dispatchers.IO) {
            try {
                connection = DriverManager.getConnection(
                    "jdbc:postgresql://192.168.43.185:5432/postgres",
                    "postgres",
                    "1905"
                )
                println("Bağlantı ")

                val selectStatement = connection!!.prepareStatement(
                    "SELECT * FROM dinleyici WHERE dinleyici_ad = ? AND sifre = ?"
                )
                selectStatement.setString(1, kullaniciAdi)
                selectStatement.setString(2, password)
                val resultSet = selectStatement.executeQuery()

                if (resultSet.next()) {
                    // Kullanıcı bulundu
                    // UI thread'inde işlem yapabilirsiniz
                    withContext(Dispatchers.Main) {
                        navController.navigate("anaSayfa")
                    }
                } else {
                    // Kullanıcı bulunamadı
                    // UI thread'inde işlem yapabilirsiniz
                    withContext(Dispatchers.Main) {

                        showToast(context,"hatali giriş")
                    }
                }

                selectStatement.close()
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("GirisViewModel", "Veritabanı hatası: ${e.message}", e)

                // Hatayı kullanıcıya bildirebilirsiniz
                withContext(Dispatchers.Main) {
                    // Toast veya Alert Dialog gösterebilirsiniz
                }
            } finally {
                connection?.close()
            }
        }
    }

    private fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
