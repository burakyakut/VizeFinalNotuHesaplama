package com.example.dersnotuhesaplama.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dersTable")
data class DersModel(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id:Int,

    @ColumnInfo(name = "dersAdi")
    val dersAdi:String,

    @ColumnInfo(name="dersPuani")
    val dersPuani:String,

    @ColumnInfo(name="harfNotu")
    val harfNotu:String,

    @ColumnInfo(name = "vizePuani")
    val vizePuani:Int,

    @ColumnInfo(name = "finalPuani")
    val finalPuani:Int

    )