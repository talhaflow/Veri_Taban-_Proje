package com.talhakara.veri_taban_proje.Model

object RapSingleton {

    private val liste = mutableListOf<MuzikTur>()

    fun ekle(rapTur: MuzikTur) {
        liste.add(rapTur)
    }

    fun cek(): List<MuzikTur> {
        return liste
    }
}