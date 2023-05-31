package com.example.dersnotuhesaplama

import android.annotation.SuppressLint
import android.content.Intent
import android.icu.text.DecimalFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.dersnotuhesaplama.databinding.ActivityHesapEkraniBinding
import com.example.dersnotuhesaplama.room.DersDao
import com.example.dersnotuhesaplama.room.DersDatabase
import com.example.dersnotuhesaplama.room.DersModel

class HesapEkrani : AppCompatActivity() {
    private lateinit var binding: ActivityHesapEkraniBinding
    private lateinit var dersDatabase: DersDatabase
    private lateinit var dersDao: DersDao
    var id:Int=-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHesapEkraniBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dersDatabase= DersDatabase.getDersDatabase(this)!!
        dersDao=dersDatabase.getDersDao()

        val type=intent.getStringExtra("type")
        if (type.equals("update")){  //type değeri update olduğu için güncelleme ekranı açılacak ve aşağıdaki veriler bu ekrana aktarılacak

            val dersAdi=intent.getStringExtra("dersAdi")
            val dersPuani=intent.getStringExtra("dersPuani")
            val harfNotu=intent.getStringExtra("harfNotu")
            val vizePuani=intent.getIntExtra("vizePuani",0).toString()
            val finalPuani=intent.getIntExtra("finalPuani",0).toString()
            id=intent.getIntExtra("id",-1)

            binding.dersAdieditText.setText(dersAdi)
            binding.dersPuani.text = dersPuani
            binding.harfNotu.text=harfNotu
            binding.vizeEditText.setText(vizePuani)
            binding.finalEditText.setText(finalPuani)

            binding.saveButton.isEnabled=false //güncelleme ekranında hesapla butonuna tıklanana kadar save butonu aktif halde olmayacak
        }

        puanHesabi()
        saveButton()
    }

    @SuppressLint("SetTextI18n")
    private fun puanHesabi() {

        binding.hesaplaButton.setOnClickListener {

        val vize = binding.vizeEditText.text.toString().toIntOrNull()
        val final = binding.finalEditText.text.toString().toIntOrNull()

        if (vize != null && final != null && vize<=100 && final<=100) {
            val vizeYuzde = when (binding.radioGroup.checkedRadioButtonId) {
                R.id.radioButtonVize1 -> 0.30
                R.id.radioButtonVize2 -> 0.40
                else -> 0.50
            }
            val vizeHesabi = vize * vizeYuzde //girilen vize puanı ile seçilen vize yüzdesini çarparak vize puanı hesaplandı
            binding.vizeTextview.text = DecimalFormat("0.#").format(vizeHesabi).toString()

            val finalYuzde = when (binding.radioGroup2.checkedRadioButtonId) {
                R.id.radioButtonFinal1 -> 0.50
                R.id.radioButtonFinal2 -> 0.60
                else -> 0.70
            }
            val finalHesabi = final * finalYuzde //girilen final puanı ile seçilen final yüzdesini çarparak final puanı hesaplandı
            binding.finalTextview.text =DecimalFormat("0.#").format(finalHesabi).toString()

            val dersPuaniHesabi = vizeHesabi + finalHesabi //dersin toplam puanı hesaplandı
            binding.dersPuani.text = DecimalFormat("0.#").format(dersPuaniHesabi).toString()

            when(dersPuaniHesabi){  //dersin toplam puanına karşılık gelen harf notu bulundu

                in 85.0..100.0->binding.harfNotu.text = "AA"
                in 70.0..85.0->binding.harfNotu.text = "BB"
                in 50.0..70.0->binding.harfNotu.text = "CC"
                in 0.0..50.0->binding.harfNotu.text = "DD"
            }

        }
            binding.saveButton.isEnabled=true //güncelleme ekranında hesapla butonuna tıklandığı için save butonu aktif hale geldi

    }

    }

    private fun saveButton(){

        binding.saveButton.setOnClickListener {

            val dersAdi=binding.dersAdieditText.text.toString()
            val dersNotu=binding.harfNotu.text.toString()
            val dersPuani=binding.dersPuani.text.toString()
            val vizePuani=binding.vizeEditText.text.toString().toIntOrNull()
            val finalPuani=binding.finalEditText.text.toString().toIntOrNull()

            val type=intent.getStringExtra("type")
            if (dersAdi.isNotEmpty() && vizePuani!=null && finalPuani!=null && dersPuani!=""){

                if (type.equals("new")){ //type değeri new ise yeni ders eklenecek
                    dersDao.insert(DersModel(
                        0, dersAdi, dersNotu, dersPuani, vizePuani, finalPuani
                    ))
                }else{ //type değeri update ise ders güncellenecek
                    dersDao.update(DersModel(
                            id, dersAdi, dersPuani, dersNotu, vizePuani, finalPuani
                    ))
                }
                finish()
            }
        }
    }
}
