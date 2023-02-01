package com.abit.taskapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null,
    var desc:String? = null,
    var title:String? = null,


):java.io.Serializable
