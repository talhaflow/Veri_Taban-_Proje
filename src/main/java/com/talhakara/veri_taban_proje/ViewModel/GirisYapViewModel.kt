package com.talhakara.veri_taban_proje.ViewModel

import java.sql.Connection
import java.sql.DriverManager

class GirisYapViewModel {


    fun girisYap(kullaniciAdi: String, sifre: String): Boolean {
        try {
            val jdbcUrl = "jdbc:postgresql://localhost:5432/postgres"
            val dbUser = "postgres"
            val dbpassword = "1905"

            var connection: Connection? = null

            try {
                connection = DriverManager.getConnection(jdbcUrl, dbUser, dbpassword)
                println("Bağlantı başarılı")


                // Kullanıcı adını kontrol et
                val userExists = kullaniciAdiVarMi(connection, kullaniciAdi)

                if (userExists) {
                    // SQL sorgusunu parametrelerle oluştur
                    val sql = "SELECT * FROM dinleyici WHERE dinleyici_ad = ? AND sifre = ?"
                    val preparedStatement = connection.prepareStatement(sql)
                    preparedStatement.setString(1, kullaniciAdi)
                    preparedStatement.setString(2, sifre)

                    val resultSet = preparedStatement.executeQuery()

                    val sifreEslesiyor = resultSet.next()

                    resultSet.close()
                    preparedStatement.close()

                    return sifreEslesiyor
                } else {
                    println("Kullanıcı adı bulunamadı.")
                }

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

    fun kullaniciAdiVarMi(connection: Connection, kullaniciAdi: String): Boolean {
        val sql = "SELECT COUNT(*) FROM dinleyici WHERE dinleyici_ad = ?"
        val preparedStatement = connection.prepareStatement(sql)
        preparedStatement.setString(1, kullaniciAdi)
        val resultSet = preparedStatement.executeQuery()

        resultSet.next()
        val kullaniciSayisi = resultSet.getInt(1)

        resultSet.close()
        preparedStatement.close()

        return kullaniciSayisi > 0
    }



}


