package com.talhakara.veri_taban_proje.Model

object PopSingleton {

    private val liste = mutableListOf<MuzikTur>()

    fun ekle(popTur: MuzikTur) {
        liste.add(popTur)
    }

    fun cek(): List<MuzikTur> {
        return liste
    }
}