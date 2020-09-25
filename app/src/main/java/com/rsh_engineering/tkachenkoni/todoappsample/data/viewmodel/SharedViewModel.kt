package com.rsh_engineering.tkachenkoni.todoappsample.data.viewmodel

import android.app.Application
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.rsh_engineering.tkachenkoni.todoappsample.R
import com.rsh_engineering.tkachenkoni.todoappsample.data.entity.RecordToDo
import com.rsh_engineering.tkachenkoni.todoappsample.data.models.Priority

/**
 * Created by Nikolay Tkachenko on 25, September, 2020
 *
 **/
class SharedViewModel(app: Application): AndroidViewModel(app) {

    val noRecordsInDatabase : MutableLiveData<Boolean> = MutableLiveData(true)

    fun checkIfDatabaseHasNoRecord(records: List<RecordToDo>){
        noRecordsInDatabase.value = records.isEmpty()
    }

    val listener: AdapterView.OnItemSelectedListener = object : AdapterView.OnItemSelectedListener{
        override fun onNothingSelected(parent: AdapterView<*>?) {

        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            when(position){
                0 -> {(parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(app, R.color.red))}
                1 -> {(parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(app, R.color.yellow))}
                2 -> {(parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(app, R.color.green))}
            }
        }

    }


    fun verifyDataFromUser(title: String, descr: String):Boolean{
        return if(TextUtils.isEmpty(title) || TextUtils.isEmpty(descr)){
            false
        } else !(title.isEmpty() || descr.isEmpty())
    }


    fun parsePriority(priority: String): Priority {
        return  when(priority){
            "High Priority" -> {
                Priority.HIGH}
            "Medium Priority" -> {
                Priority.MEDIUM}
            "Low Priority" -> {
                Priority.LOW}
            else -> Priority.LOW
        }
    }

    fun parsePriorityToInt(priority:Priority): Int{
        return when(priority){
            Priority.HIGH -> 0
            Priority.MEDIUM -> 3
            Priority.LOW -> 2
        }
    }

}