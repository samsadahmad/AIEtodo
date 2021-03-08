package com.aie.aietodo.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * This entity data class to create task table in DB
 * with id primary key & task_name unique
 */
@Entity(tableName = "tasks", indices = [Index(value = ["task_name"], unique = true)])
data class Task(

    @PrimaryKey(autoGenerate = true)
    val id: Long,

    @ColumnInfo(name = "task_name")
    var taskName: String,

    @ColumnInfo(name = "task_creator")
    var taskCreator: String,

    @ColumnInfo(name = "task_date")
    var taskDate: String
){
    constructor(taskName: String, taskCreator: String, taskDate: String) : this(0, taskName, taskCreator, taskDate)
}