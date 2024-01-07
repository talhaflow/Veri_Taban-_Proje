package com.talhakara.veri_taban_proje.Model

object ArabeskSingleton {

     private val liste = mutableListOf<MuzikTur>()

    fun ekle(arabeskTur: MuzikTur) {
        liste.add(arabeskTur)
    }

    fun cek(): List<MuzikTur> {
        return liste
    }
}