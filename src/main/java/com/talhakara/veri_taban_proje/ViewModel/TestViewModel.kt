package com.talhakara.veri_taban_proje.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.talhakara.veri_taban_proje.Model.Cevap
import com.talhakara.veri_taban_proje.Model.CevapDeposu
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Statement

class TestViewModel : ViewModel() {
    data class GelenCevap(val soruNo: Int, val secilenCevap: String)



    fun kullaniciSonCevabiCek(kullaniciId: Int, soruId: Int): Cevap? {
        var sonCevap: Cevap? = null
        var mevcutCevap: Cevap?= null
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val jdbcUrl = "jdbc:postgresql://192.168.43.185:5432/postgres"
                val dbUser = "postgres"
                val dbpassword = "1905"
                var connection: Connection? = null
                try {
                    connection = DriverManager.getConnection(jdbcUrl, dbUser, dbpassword)
                    println("Bağlantı başarılı")
                    val sql = "SELECT soru_id, soru_sikki FROM cevaplar WHERE kullanici_id = ? AND soru_id = ? ORDER BY id DESC LIMIT 1"
                    val preparedStatement: PreparedStatement = connection.prepareStatement(sql)
                    preparedStatement.setInt(1, kullaniciId)
                    preparedStatement.setInt(2, soruId)
                    val resultSet: ResultSet = preparedStatement.executeQuery()
                    if (resultSet.next()) {

                        mevcutCevap= Cevap(
                            resultSet.getInt("soru_id"),
                            resultSet.getString("soru_sikki")
                        )

                    }
                    preparedStatement.close()
                    // Veritabanı işlemi başarılı, UI thread'inde işlem yapabilirsiniz
                    withContext(Dispatchers.Main) {

                        if (mevcutCevap != null) {
                            CevapDeposu.ekle(mevcutCevap!!)
                        }


                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    // Hatayı kullanıcıya bildirebilirsiniz
                    withContext(Dispatchers.Main) {
                        // Toast veya Alert Dialog gösterebilirsiniz
                    }
                } finally {
                    connection?.close()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        return sonCevap
    }







    fun cevapKaydet(cevap: Cevap, id: Int): Boolean {
        if (cevap.soruNo !is Int || cevap.secilenCevap !is String) {
            println("Cevap doğru formatta değil.")
            return false
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val jdbcUrl = "jdbc:postgresql://192.168.43.185:5432/postgres"
                val dbUser = "postgres"
                val dbpassword = "1905"

                if (jdbcUrl.isBlank() || dbUser.isBlank() || dbpassword.isBlank()) {
                    println("Veritabanına erişim için gerekli bilgiler eksik.")
                }

                var connection: Connection? = null

                try {
                    connection = DriverManager.getConnection(jdbcUrl, dbUser, dbpassword)
                    println("Bağlantı başarılı")

                    val sql = "INSERT INTO cevaplar (soru_id, soru_sikki, kullanici_id) VALUES (?, ?, ?)"
                    val preparedStatement: PreparedStatement = connection.prepareStatement(sql)

                    preparedStatement.setInt(1, cevap.soruNo)
                    preparedStatement.setString(2, cevap.secilenCevap)
                    preparedStatement.setInt(3, id)

                    val affectedRows = preparedStatement.executeUpdate()

                    preparedStatement.close()

                    // Veritabanı işlemi başarılı, UI thread'inde işlem yapabilirsiniz
                    withContext(Dispatchers.Main) {
                        // Kullanıcıya başarılı olduğunu bildirebilirsiniz
                    }
                } catch (e: Exception) {
                    e.printStackTrace()

                    // Hatayı kullanıcıya bildirebilirsiniz
                    withContext(Dispatchers.Main) {
                        // Toast veya Alert Dialog gösterebilirsiniz
                    }
                } finally {
                    connection?.close()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return true
    }





}

data class Cevaplar(
    val soruId: Int,
    val soruSikki: String,
    val kullaniciId: Int
)

data class Sorular(
    val soru_id: Int,
    val soru_metni: String,
    val secenek_a: String,
    val secenek_b: String,
    val secenek_c: String,
    val secenek_d: String,
)

fun sorulariCek() {
    val sorularListesi = mutableListOf<Sorular>()

    try {
        val jdbcUrl = "jdbc:postgresql://192.168.43.185:5432/postgres"
        val dbUser = "postgres"
        val dbpassword = "1905"

        var connection: Connection? = null

        try {
            connection = DriverManager.getConnection(jdbcUrl, dbUser, dbpassword)
            println("Bağlantı başarılı")

            val sql = "SELECT soru_id, soru_metni, secenek_a, secenek_b, secenek_c, secenek_d FROM testler"
            val statement = connection.createStatement()
            val resultSet = statement.executeQuery(sql)

            while (resultSet.next()) {
                val soru = Sorular(
                    resultSet.getInt("soru_id"),
                    resultSet.getString("soru_metni"),
                    resultSet.getString("secenek_a"),
                    resultSet.getString("secenek_b"),
                    resultSet.getString("secenek_c"),
                    resultSet.getString("secenek_d")
                )
                sorularListesi.add(soru)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            connection?.close()
        }

    } catch (e: Exception) {
        e.printStackTrace()
    }

    for(soru in sorularListesi){
        println("${soru.soru_id}"+""+soru.soru_metni)
        println("A"+soru.secenek_a)
        println("B"+soru.secenek_b)
        println("C"+soru.secenek_c)
        println("D"+soru.secenek_d)
        println("---------------------------------")
    }


}


