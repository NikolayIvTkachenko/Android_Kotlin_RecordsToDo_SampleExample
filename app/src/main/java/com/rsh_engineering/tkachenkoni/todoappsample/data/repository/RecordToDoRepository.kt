package com.rsh_engineering.tkachenkoni.todoappsample.data.repository

import androidx.lifecycle.LiveData
import com.rsh_engineering.tkachenkoni.todoappsample.data.dao.RecordToDoDao
import com.rsh_engineering.tkachenkoni.todoappsample.data.entity.RecordToDo

/**
 * Created by Nikolay Tkachenko on 25, September, 2020
 *
 **/
class RecordToDoRepository(private val recordDao: RecordToDoDao) {

    val getAllDate : LiveData<List<RecordToDo>> = recordDao.getAllData()

    suspend fun insertRecord(recordToDo: RecordToDo){
        recordDao.insertRecord(recordToDo)
    }

    suspend fun updateRecord(recordToDo: RecordToDo){
        recordDao.updateRecord(recordToDo)
    }

    suspend fun deleteRecord(recordToDo: RecordToDo){
        recordDao.deleteRecord(recordToDo)
    }

    suspend fun deleteAllRecords(){
        recordDao.deleteAllRecords()
    }

}