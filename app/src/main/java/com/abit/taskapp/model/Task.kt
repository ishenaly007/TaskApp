package com.abit.taskapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abit.taskapp.R
import java.io.Serializable

@Entity
data class Task(

    @PrimaryKey(autoGenerate = true)
    var id:Int? = null,
    var desc:String? = null,
    var title:String? = null,


): Serializable