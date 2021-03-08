package com.aie.aietodo.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aie.aietodo.data.db.AppDatabase
import com.aie.aietodo.data.db.entity.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @class : TaskRepository
 * @desc This class created for Task repository
 * It will hold methods related for task repo
 */
class TaskRepository(
    private val db: AppDatabase
) {

    /**
     * @method : getTask
     * @desc : this method will fetch data from DB
     * @return : live data
     */
    suspend fun getTask() : LiveData<List<Task>> {
        return withContext(Dispatchers.IO){
            db.getTaskDao().getTask()
        }
    }
    /**
     * @method : getTask
     * @desc : this method will fetch data from DB
     * @return : live data
     */
    suspend fun getTaskById(id: Long) : Task {
        return db.getTaskDao().getTaskById(id)
    }
    /**
     * @method : getTask
     * @desc : this method use for save task in database
     * @param : Task
     * @return : live data
     */
    suspend fun saveTask(task: Task): Long = db.getTaskDao().insert(task)
    /**
     * @method : updateTask
     * @desc : this method use for update task in database
     * @param : Task
     * @return : live data
     */
    suspend fun updateTask(task: Task) = db.getTaskDao().update(task)

    /**
     * @method : deleteTask
     * @desc : this method use for delete task in database
     * @param : Task
     * @return : live data
     */
    suspend fun deleteTask(task: Task) = db.getTaskDao().deleteTask(task)
}