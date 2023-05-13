package com.example.bayrakquizuygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.bayrakquizuygulamasi.databinding.ActivityQuizBinding

class quizActivity : AppCompatActivity() {
    private lateinit var sorular:ArrayList<Bayraklar>
    private lateinit var yanlisSecenekler:ArrayList<Bayraklar>
    private lateinit var dogruSoru:Bayraklar
    private lateinit var tumSecenekler:HashSet<Bayraklar>
    private lateinit var vt:VeritabaniYardimcisi

    private var soruSayac = 0
    private var dogruSayac = 0
    private var yanlisSayac = 0

    private lateinit var tasarim:ActivityQuizBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tasarim = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(tasarim.root)

        vt = VeritabaniYardimcisi(this)

        sorular = Bayraklardao().rastgeleBayrakGetir(vt)
        soruYukle()

        tasarim.buttonA.setOnClickListener {
            dogruKontrol(tasarim.buttonA)
            soruSayacKontrol()
        }
        tasarim.buttonB.setOnClickListener {
            dogruKontrol(tasarim.buttonB)
            soruSayacKontrol()
        }
        tasarim.buttonC.setOnClickListener {
            dogruKontrol(tasarim.buttonC)
            soruSayacKontrol()
        }
        tasarim.buttonD.setOnClickListener {
            dogruKontrol(tasarim.buttonD)
            soruSayacKontrol()
        }
    }



    fun soruYukle(){
        tasarim.textViewSoruSayi.text = "${soruSayac+1}. Soru"

        dogruSoru = sorular.get(soruSayac)
        tasarim.imageViewBayrak.setImageResource(resources.getIdentifier(dogruSoru.bayrak_resim,"drawable",packageName))

        yanlisSecenekler = Bayraklardao().rastgeleYanlisSecenek(vt,dogruSoru.bayrak_id)
        tumSecenekler = HashSet()
        tumSecenekler.add(dogruSoru)
        tumSecenekler.add(yanlisSecenekler.get(0))
        tumSecenekler.add(yanlisSecenekler.get(1))
        tumSecenekler.add(yanlisSecenekler.get(2))
        tasarim.buttonA.text = tumSecenekler.elementAt(0).bayrak_ad
        tasarim.buttonB.text = tumSecenekler.elementAt(1).bayrak_ad
        tasarim.buttonC.text = tumSecenekler.elementAt(2).bayrak_ad
        tasarim.buttonD.text = tumSecenekler.elementAt(3).bayrak_ad
    }

    fun soruSayacKontrol(){
        soruSayac++
        if (soruSayac != 5){
            soruYukle()
        }else{
            val intent = Intent(this@quizActivity,ResultActivity::class.java)
            intent.putExtra("dogruSayac",dogruSayac)
            startActivity(Intent(intent))
            finish()

        }
    }

    fun dogruKontrol(button:Button){
        val buttonYazi = button.text.toString()
        val dogruCevap = dogruSoru.bayrak_ad

        if (buttonYazi == dogruCevap){
            dogruSayac++
        }else{
            yanlisSayac++
        }

        tasarim.textViewDogru.text = "Doğru : $dogruSayac"
        tasarim.textViewYanlis.text = "Yanlış : $yanlisSayac"
    }
}