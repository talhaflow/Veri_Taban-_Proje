package com.talhakara.veri_taban_proje.Model

object CevapDeposu {

    private val liste = mutableListOf<Cevap>()

    fun ekle(cevap: Cevap) {
        liste.add(cevap)
    }

    fun cek(): List<Cevap> {
        return liste
    }
}
