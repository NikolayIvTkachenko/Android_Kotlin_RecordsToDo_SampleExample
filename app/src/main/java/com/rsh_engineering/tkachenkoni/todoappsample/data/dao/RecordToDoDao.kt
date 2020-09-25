package com.rsh_engineering.tkachenkoni.todoappsample.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rsh_engineering.tkachenkoni.todoappsample.data.entity.RecordToDo

/**
 * Created by Nikolay Tkachenko on 24, September, 2020
 *
 **/
@Dao
interface RecordToDoDao {

    @Query("SELECT * FROM record_table ORDER BY id ASC")
    fun getAllData(): LiveData<List<RecordToDo>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRecord(recordToDo: RecordToDo)

    @Update
    suspend fun updateRecord(record: RecordToDo)

    @Delete
    suspend fun deleteRecord(record: RecordToDo)

    @Query("DELETE FROM record_table")
    suspend fun deleteAllRecords()

}