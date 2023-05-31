package com.example.dersnotuhesaplama.room

import androidx.room.*

@Dao
interface DersDao {

    @Insert
    fun insert(dersModel: DersModel)

    @Update
    fun update(dersModel: DersModel)

    @Delete
    fun delete(dersModel: DersModel)

    @Query("SELECT * FROM dersTable")
    fun getAll():List<DersModel>
}