package com.example.dersnotuhesaplama

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dersnotuhesaplama.adapter.DersAdapter
import com.example.dersnotuhesaplama.databinding.ActivityKayitliDerslerBinding
import com.example.dersnotuhesaplama.room.DersDao
import com.example.dersnotuhesaplama.room.DersDatabase
import com.example.dersnotuhesaplama.room.DersModel

class KayitliDersler : AppCompatActivity() {
    private lateinit var binding: ActivityKayitliDerslerBinding
    private lateinit var dersList:ArrayList<DersModel>
    private lateinit var dersDatabase: DersDatabase
    private lateinit var dersDao: DersDao
    private lateinit var dersAdapter: DersAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityKayitliDerslerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dersList=ArrayList<DersModel>()
        dersDatabase= DersDatabase.getDersDatabase(this)!!
        dersDao=dersDatabase.getDersDao()
        dersAdapter=DersAdapter(dersList)
        binding.derslerRecyclerView.layoutManager=LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        binding.derslerRecyclerView.adapter=dersAdapter
        delete()
    }

    override fun onResume() {
        super.onResume()
        getData()
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun getData(){
        dersList.clear()
        dersList.addAll(dersDao.getAll())
        dersAdapter.notifyDataSetChanged()

    }

    private fun delete(){
        dersAdapter.onItemClick={
            dersDao.delete(it)
            getData()
        }
    }
}