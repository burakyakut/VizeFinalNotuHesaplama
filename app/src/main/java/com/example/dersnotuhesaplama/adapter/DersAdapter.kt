package com.example.dersnotuhesaplama.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dersnotuhesaplama.HesapEkrani
import com.example.dersnotuhesaplama.databinding.RecyclerrowBinding
import com.example.dersnotuhesaplama.room.DersModel

class DersAdapter(private val dersList:ArrayList<DersModel>):RecyclerView.Adapter<DersAdapter.DersHolder>() {
    var onItemClick:((DersModel)->Unit)?=null

    class DersHolder(val recyclerrowBinding: RecyclerrowBinding):RecyclerView.ViewHolder(recyclerrowBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DersHolder {
        return DersHolder(RecyclerrowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return dersList.size
    }

    override fun onBindViewHolder(holder: DersHolder, position: Int) {
        val ders=dersList[position]
        holder.recyclerrowBinding.cardViewDersAdiTv.text=ders.dersAdi
        holder.recyclerrowBinding.cardViewDersPuaniTv.text=ders.dersPuani
        holder.recyclerrowBinding.cardViewHarfNotuTv.text=ders.harfNotu
        holder.recyclerrowBinding.cardViewVizeTv.text=ders.vizePuani.toString()
        holder.recyclerrowBinding.cardViewFinalTv.text=ders.finalPuani.toString()

        holder.recyclerrowBinding.cardViewDelete.setOnClickListener {
            onItemClick?.invoke(ders)
        }

        holder.itemView.setOnClickListener {
            val intent=Intent(holder.itemView.context,HesapEkrani::class.java)
            intent.putExtra("type","update")
            intent.putExtra("id",ders.id)
            intent.putExtra("dersAdi",ders.dersAdi)
            intent.putExtra("dersPuani",ders.dersPuani)
            intent.putExtra("harfNotu",ders.harfNotu)
            intent.putExtra("vizePuani",ders.vizePuani)
            intent.putExtra("finalPuani",ders.finalPuani)

            holder.itemView.context.startActivity(intent)
        }
    }
}