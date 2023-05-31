package com.example.dersnotuhesaplama.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.util.joinIntoString

@Database(entities = [DersModel::class], version = 1, exportSchema = true)
abstract class DersDatabase:RoomDatabase() {

    abstract fun getDersDao():DersDao

    companion object{
        @Volatile
        private var INSTANCE:DersDatabase?=null

        fun getDersDatabase(context: Context):DersDatabase?{
            return INSTANCE?:synchronized(this){
                val instance=Room.databaseBuilder(
                    context.applicationContext,
                    DersDatabase::class.java,
                    "dersDatabase"

                )   .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE=instance
                instance

            }
        }
    }
}
