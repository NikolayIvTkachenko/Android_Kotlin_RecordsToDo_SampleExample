package com.rsh_engineering.tkachenkoni.todoappsample.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.rsh_engineering.tkachenkoni.todoappsample.data.RecordToDoDatabase
import com.rsh_engineering.tkachenkoni.todoappsample.data.entity.RecordToDo
import com.rsh_engineering.tkachenkoni.todoappsample.data.repository.RecordToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Nikolay Tkachenko on 25, September, 2020
 *
 **/
class RecordToDoViewModel(app: Application): AndroidViewModel(app) {

    private val recordToDoDao = RecordToDoDatabase.getDatabase(
        app
    ).recordToDoDao()
    private val repository: RecordToDoRepository

    val getAllData: LiveData<List<RecordToDo>>

    init{
        repository = RecordToDoRepository((recordToDoDao))
        getAllData = repository.getAllDate
    }

    fun insertRecord(recordToDo: RecordToDo){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertRecord(recordToDo)
        }
    }

    fun updateRecord(recordToDo: RecordToDo){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateRecord(recordToDo)
        }
    }

    fun deletedRecord(recordToDo: RecordToDo){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteRecord(recordToDo)
        }
    }

    fun deletedAllRecords(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllRecords()
        }
    }

}