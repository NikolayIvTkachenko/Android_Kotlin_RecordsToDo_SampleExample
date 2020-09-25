package com.rsh_engineering.tkachenkoni.todoappsample.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rsh_engineering.tkachenkoni.todoappsample.data.models.Priority
import kotlinx.android.parcel.Parcelize

/**
 * Created by Nikolay Tkachenko on 24, September, 2020
 *
 **/
@Entity(tableName = "record_table")
@Parcelize
data class RecordToDo(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var priority: Priority,
    var description: String
):Parcelable