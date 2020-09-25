package com.rsh_engineering.tkachenkoni.todoappsample.data

import android.content.Context
import androidx.room.*
import com.rsh_engineering.tkachenkoni.todoappsample.data.dao.RecordToDoDao
import com.rsh_engineering.tkachenkoni.todoappsample.data.entity.RecordToDo
import com.rsh_engineering.tkachenkoni.todoappsample.data.models.Converter

/**
 * Created by Nikolay Tkachenko on 24, September, 2020
 *
 **/
@Database(entities = [RecordToDo::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class RecordToDoDatabase: RoomDatabase() {
    abstract fun recordToDoDao(): RecordToDoDao

    companion object {

        @Volatile
        private var INSTANCE: RecordToDoDatabase? = null

        fun getDatabase(context: Context): RecordToDoDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecordToDoDatabase::class.java,
                    "records_todo_database"
                ).build()
                INSTANCE = instance
                return  instance
            }
        }
    }

}