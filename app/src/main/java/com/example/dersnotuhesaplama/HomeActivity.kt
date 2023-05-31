package com.example.dersnotuhesaplama

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dersnotuhesaplama.adapter.DersAdapter
import com.example.dersnotuhesaplama.databinding.ActivityHomeBinding
import com.example.dersnotuhesaplama.room.DersDao
import com.example.dersnotuhesaplama.room.DersDatabase
import com.example.dersnotuhesaplama.room.DersModel

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.yeniDersTv.setOnClickListener {
            val intent=Intent(this,HesapEkrani::class.java)
            intent.putExtra("type","new")
            startActivity(intent)
        }

        binding.kayitliDersTv.setOnClickListener {
            val intent=Intent(this,KayitliDersler::class.java)
            startActivity(intent)
        }
    }




}