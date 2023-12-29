package com.talhakara.veri_taban_proje.ViewModel

import androidx.lifecycle.ViewModel
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Statement

class TestViewModel : ViewModel() {

    // Soru ve cevapları içeren liste örneği
    val cevapListesi = listOf(
        Cevaplar(soruId = 1, soruSikki = "A", kullaniciId = 1),
        Cevaplar(soruId = 2, soruSikki = "B", kullaniciId = 2)
        // ... Diğer sorular ve cevaplar
    )

    init {
        cevapListesiniKaydet(cevapListesi)
    }
}

data class Cevaplar(
    val soruId: Int,
    val soruSikki: String,
    val kullaniciId: Int
)

fun cevapListesiniKaydet(cevapListesi: List<Cevaplar>): Boolean {
    try {
        val jdbcUrl = "jdbc:postgresql://localhost:5432/postgres"
        val dbUser = "postgres"
        val dbpassword = "1905"

        var connection: Connection? = null

        try {
            connection = DriverManager.getConnection(jdbcUrl, dbUser, dbpassword)
            println("Bağlantı başarılı")

            val sql = "INSERT INTO cevaplar (soru_id, soru_sikki, kullanici_id) VALUES (?, ?, ?)"

            val preparedStatement: PreparedStatement = connection.prepareStatement(sql)

            // Her bir Cevaplar nesnesi için veritabanına ekleme işlemi
            for (cevap in cevapListesi) {
                preparedStatement.setInt(1, cevap.soruId)
                preparedStatement.setString(2, cevap.soruSikki)
                preparedStatement.setInt(3, cevap.kullaniciId)

                preparedStatement.addBatch() // Batch işlemler için eklenen satır
            }

            // Batch işlemleri gerçekleştir
            val affectedRows = preparedStatement.executeBatch().sum()

            preparedStatement.close()

            return affectedRows > 0
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            connection?.close()
        }

    } catch (e: Exception) {
        e.printStackTrace()
    }

    return false
}

fun testleriCekme() {
    try {
        val jdbcUrl = "jdbc:postgresql://localhost:5432/postgres"
        val dbUser = "postgres"
        val dbpassword = "1905"

        var connection: Connection? = null

        try {
            connection = DriverManager.getConnection(jdbcUrl, dbUser, dbpassword)
            println("Bağlantı başarılı")

            // Tüm özellikleri içeren sorgu
            val sql = "SELECT * FROM testler"

            val statement: Statement = connection.createStatement()
            val resultSet: ResultSet = statement.executeQuery(sql)

            // ResultSet üzerinde dönerek verileri al
            while (resultSet.next()) {
                val id = resultSet.getInt("soru_id")
                val soru = resultSet.getString("soru_metni")
                val a = resultSet.getString("secenek_a")
                val b = resultSet.getString("secenek_b")
                val c = resultSet.getString("secenek_c")
                val d = resultSet.getString("secenek_d")

                // Elde edilen verileri kullanabilirsiniz
                println("$id, soru: $soru")
                println("a)$a")
                println("b)$b")
                println("c)$c")
                println("d)$d")
                println()
                println()
            }

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            connection?.close()
        }

    } catch (e: Exception) {
        e.printStackTrace()
    }
}
