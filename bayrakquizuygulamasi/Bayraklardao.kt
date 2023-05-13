package com.example.bayrakquizuygulamasi

import android.annotation.SuppressLint

class Bayraklardao() {

    @SuppressLint("Range")
    fun rastgeleBayrakGetir(vt: VeritabaniYardimcisi): ArrayList<Bayraklar> {
        val bayrakList = ArrayList<Bayraklar>()
        val db = vt.writableDatabase
        val c = db.rawQuery("SELECT * FROM bayraklar ORDER BY RANDOM() LIMIT 5", null,)

        while (c.moveToNext()) {
            val bayrak = Bayraklar(
                c.getInt(c.getColumnIndex("bayrak_id")),
                c.getString(c.getColumnIndex("bayrak_ad")),
                c.getString(c.getColumnIndex("bayrak_resim"))
            )
            bayrakList.add(bayrak)
        }
        return bayrakList
    }

    @SuppressLint("Range")
    fun rastgeleYanlisSecenek(vt: VeritabaniYardimcisi, bayrak_id: Int): ArrayList<Bayraklar> {
        val bayrakList = ArrayList<Bayraklar>()
        val db = vt.writableDatabase
        val c = db.rawQuery(
            "SELECT * FROM bayraklar WHERE bayrak_id != $bayrak_id ORDER BY RANDOM() LIMIT 3",
            null,
        )

        while (c.moveToNext()) {
            val bayrak = Bayraklar(
                c.getInt(c.getColumnIndex("bayrak_id")),
                c.getString(c.getColumnIndex("bayrak_ad")),
                c.getString(c.getColumnIndex("bayrak_resim"))
            )
            bayrakList.add(bayrak)
        }
        return bayrakList
    }
}
