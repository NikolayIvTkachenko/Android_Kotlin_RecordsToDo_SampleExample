package com.rsh_engineering.tkachenkoni.todoappsample.data.models

import androidx.room.TypeConverter

/**
 * Created by Nikolay Tkachenko on 25, September, 2020
 *
 **/
class Converter {

    @TypeConverter
    fun fromPriority(priority: Priority):String{
        return priority.name
    }

    @TypeConverter
    fun toPriority(priority: String):Priority{
        return Priority.valueOf(priority)
    }


}