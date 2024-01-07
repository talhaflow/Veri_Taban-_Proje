package com.talhakara.veri_taban_proje.View


import androidx.lifecycle.viewModelScope
import com.talhakara.veri_taban_proje.Model.Cevap
import com.talhakara.veri_taban_proje.Model.MuzikTur
import com.talhakara.veri_taban_proje.ViewModel.Cevaplar
import com.talhakara.veri_taban_proje.ViewModel.Sorular
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Statement


fun main() {

   val adams: MutableList<Cevap> = mutableListOf()
    adams.add(Cevap(0,"A"))
    adams.add(Cevap(1,"B"))
    adams.add(Cevap(2,"C"))
    adams.add(Cevap(3,"B"))
    adams.add(Cevap(4,"A"))
    adams.add(Cevap(5,"B"))
    adams.add(Cevap(6,"C"))
    adams.add(Cevap(7,"A"))
    adams.add(Cevap(8,"D"))
    adams.add(Cevap(9,"D"))




 /*   val rapListesi: List<MuzikTur> = getRapListesi()

    println( rapListesi.random().sarki_ad)
    println( rapListesi.random().sarki_ad)
    println( rapListesi.random().sarki_ad)
    println( rapListesi.random().sarki_ad)*/


    // Elde edilen rap listesini kullanabilirsiniz.
    /*for (rap in rapListesi) {
      //  print("  ${rap.sanatci_id}")
        print("${rap.sarki_id}")
        print("${rap.sarki_ad}")
        println()
    }*/


}

fun puanToPlaylist(puanlar:List<Int>){
    val rapListesi: List<MuzikTur> = getRapListesi()
    val popListesi: List<MuzikTur> = getPopListesi()
    val rockListesi: List<MuzikTur> = getRockListesi()
    val arabeskList: List<MuzikTur> = getArabeskListesi()

    for (i in 0 until puanlar[0]) {
        println(rapListesi.random().sarki_ad)
    }
    println()

    for (i in 0 until puanlar[1]) {
        println(popListesi.random().sarki_ad)

    }
    println()

    for (i in 0 until puanlar[2]) {
        println(rockListesi.random().sarki_ad)

    }
    println()

    for (i in 0 until puanlar[3]) {
        println(arabeskList.random().sarki_ad)
    }



}

fun getArabeskListesi(): List<MuzikTur> {
    val jdbcUrl = "jdbc:postgresql://192.168.43.185:5432/postgres"
    val dbUser = "postgres"
    val dbpassword = "1905"

    var arabeskListesi = listOf<MuzikTur>()
    var connection: Connection? = null

    try {
        connection = DriverManager.getConnection(jdbcUrl, dbUser, dbpassword)
        println("Bağlantı başarılı")

        val sql = "SELECT * FROM public.arabesk ORDER BY sarki_id ASC"
        val preparedStatement: PreparedStatement = connection.prepareStatement(sql)
        val resultSet: ResultSet = preparedStatement.executeQuery()

        while (resultSet.next()) {
            val arabesk = MuzikTur(
                tur = "arabesk",
                sarki_id = resultSet.getInt("sarki_id"),
                sanatci_id = resultSet.getInt("sanatci_id"),
                sarki_ad = resultSet.getString("sarki_adi")
            )
            arabeskListesi = arabeskListesi.plus(arabesk)
        }

        preparedStatement.close()

    } catch (e: Exception) {
        e.printStackTrace()

    } finally {
        connection?.close()
    }

    return arabeskListesi
}

fun getPopListesi(): List<MuzikTur> {
    val jdbcUrl = "jdbc:postgresql://192.168.43.185:5432/postgres"
    val dbUser = "postgres"
    val dbpassword = "1905"

    var popListesi = listOf<MuzikTur>()
    var connection: Connection? = null

    try {
        connection = DriverManager.getConnection(jdbcUrl, dbUser, dbpassword)
        println("Bağlantı başarılı")

        val sql = "SELECT * FROM public.pop ORDER BY sarki_id ASC"
        val preparedStatement: PreparedStatement = connection.prepareStatement(sql)
        val resultSet: ResultSet = preparedStatement.executeQuery()

        while (resultSet.next()) {
            val pop = MuzikTur(
               tur = "pop",
                sarki_id = resultSet.getInt("sarki_id"),
                sanatci_id = resultSet.getInt("sanatci_id"),
                sarki_ad = resultSet.getString("sarki_adi")
            )
            popListesi = popListesi.plus(pop)
        }

        preparedStatement.close()

    } catch (e: Exception) {
        e.printStackTrace()

    } finally {
        connection?.close()
    }

    return popListesi
}

fun getRockListesi(): List<MuzikTur> {
    val jdbcUrl = "jdbc:postgresql://192.168.43.185:5432/postgres"
    val dbUser = "postgres"
    val dbpassword = "1905"

    var rockListesi = listOf<MuzikTur>()
    var connection: Connection? = null

    try {
        connection = DriverManager.getConnection(jdbcUrl, dbUser, dbpassword)
        println("Bağlantı başarılı")

        val sql = "SELECT * FROM public.rock ORDER BY sarki_id ASC"
        val preparedStatement: PreparedStatement = connection.prepareStatement(sql)
        val resultSet: ResultSet = preparedStatement.executeQuery()

        while (resultSet.next()) {
            val rock = MuzikTur(
                tur = "rock",
                sarki_id = resultSet.getInt("sarki_id"),
                sanatci_id = resultSet.getInt("sanatci_id"),
                sarki_ad = resultSet.getString("sarki_adi")
            )
            rockListesi = rockListesi.plus(rock)
        }

        preparedStatement.close()

    } catch (e: Exception) {
        e.printStackTrace()

    } finally {
        connection?.close()
    }

    return rockListesi
}

fun getRapListesi(): List<MuzikTur> {
    val jdbcUrl = "jdbc:postgresql://192.168.43.185:5432/postgres"
    val dbUser = "postgres"
    val dbpassword = "1905"

    var rapListesi = listOf<MuzikTur>()
    var connection: Connection? = null

    try {
        connection = DriverManager.getConnection(jdbcUrl, dbUser, dbpassword)
        println("Bağlantı başarılı")

        val sql = "SELECT * FROM public.rap ORDER BY sarki_id ASC"
        val preparedStatement: PreparedStatement = connection.prepareStatement(sql)
        val resultSet: ResultSet = preparedStatement.executeQuery()

        while (resultSet.next()) {
            val rap = MuzikTur(
                tur = "Rap",
                sarki_id = resultSet.getInt("sarki_id"),
                sanatci_id = resultSet.getInt("sanatci_id"),
                sarki_ad = resultSet.getString("sarki_adi")
            )
            rapListesi = rapListesi.plus(rap)
        }

        preparedStatement.close()

    } catch (e: Exception) {
        e.printStackTrace()

    } finally {
        connection?.close()
    }

    return rapListesi
}







