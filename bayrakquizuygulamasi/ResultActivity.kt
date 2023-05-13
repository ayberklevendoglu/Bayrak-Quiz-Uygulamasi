package com.example.bayrakquizuygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bayrakquizuygulamasi.databinding.ActivityQuizBinding
import com.example.bayrakquizuygulamasi.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var tasarim:ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tasarim = ActivityResultBinding.inflate(layoutInflater)
        setContentView(tasarim.root)

        val dogruSayac = intent.getIntExtra("dogruSayac",0)
        tasarim.textViewSonuc.text = "$dogruSayac DOĞRU ${5-dogruSayac} YANLIŞ"

        tasarim.textViewYuzdeSonuc.text = "%${(dogruSayac*100)/5} BAŞARI ORANI"

        tasarim.buttonTekrar.setOnClickListener {
            startActivity(Intent(this@ResultActivity,MainActivity::class.java))
            finish()
        }
    }
}