package com.aie.aietodo.data.db.entity

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {

    /**
     * @method : insert
     * @desc : this method will save data in DB
     * OnConflictStrategy.IGNORE : if data's duplicate then it will ignore inserting
     * @param : Task
     * @return : Long
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: Task): Long

    /**
     * @method : update
     * @desc : this method will update data in DB
     * @param : Task
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(task: Task)

    /**
     * @method : getTask
     * @desc : this method will select all task in livedata fromat
     * @param : Task
     */
    @Query("SELECT * FROM tasks")
    fun getTask(): LiveData<List<Task>>

    /**
     * @method : getTaskById
     * @desc : this method will get single task from DB
     * @param : Long
     * @return : Task
     */
    @Query("SELECT * FROM tasks WHERE id = :id")
    suspend fun getTaskById(id: Long): Task

    /**
     * @method : deleteTask
     * @desc : this method will delete task from DB
     * @param : Long
     * @return : Task
     */
    @Delete
    suspend fun deleteTask(task: Task)
}