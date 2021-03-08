package com.aie.aietodo.ui.task.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aie.aietodo.data.db.entity.Task
import com.aie.aietodo.data.repository.TaskRepository
import com.aie.aietodo.utils.coroutines
import com.aie.aietodo.utils.lazyDeferred

class TaskViewModel(val taskRepository: TaskRepository): ViewModel() {

    /**
     * @method : getTask
     * @desc : this method created to call repo getTask()
     * @return : live data
     */
    val tasks by lazyDeferred {
        taskRepository.getTask()
    }
    /**
     * @method : getTask
     * @desc : this method created to call repo getTaskById(id)
     * @return : live data
     */
    suspend fun  getTaskById(id: Long): Task {
        return taskRepository.getTaskById(id)
    }
    /**
     * @method : getTask
     * @desc : this method created to call repo saveTask(task)
     * @param : Task
     * @return : live data
     */
    suspend fun  saveTask(task: Task): Long {
        return taskRepository.saveTask(task)
    }
    /**
     * @method : updateTask
     * @desc : this method created to call repo updateTask(task)
     * @param : Task
     * @return : live data
     */
    suspend fun  updateTask(task: Task) {
        return taskRepository.updateTask(task)
    }
    /**
     * @method : deleteTask
     * @desc : this method created to call repo deleteTask(task)
     * @param : Task
     * @return : live data
     */
    suspend fun deleteTask(task: Task){
        taskRepository.deleteTask(task)
    }
}