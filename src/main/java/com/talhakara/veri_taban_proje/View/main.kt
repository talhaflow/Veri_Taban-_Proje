package com.talhakara.veri_taban_proje.View


import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement
import java.sql.PreparedStatement



fun main() {
//kaydetSQL("asd","aaaaaaaaaaa")
}

/*fun kaydetSQL(kullaniciAdi: String, password: String) {
    try {
        val jdbcUrl = "jdbc:postgresql://localhost:5432/postgres"
        val dbUser = "postgres"
        val dbpassword = "1905"

            var connection: Connection? = null

            try {
                connection = DriverManager.getConnection(jdbcUrl, dbUser, dbpassword)
                println("Bağlantı başarılı")

                val insertStatement =
                    connection.prepareStatement("INSERT INTO dinleyici (dinleyici_ad, sifre) VALUES (?, ?)")
                insertStatement.setString(1, kullaniciAdi)
                insertStatement.setString(2, password)
                insertStatement.executeUpdate()
                insertStatement.close()


            } catch (e: Exception) {
                e.printStackTrace()

            } finally {
                connection?.close()
            }



    } catch (e: Exception) {
        e.printStackTrace()

    }
}*/


