package com.aie.aietodo.ui.task.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aie.aietodo.data.repository.TaskRepository

/**
 * @class : TaskViewModelFactory
 * @desc : This class create to hold or pass constructor param of TaskViewModel
 * As we know that view model class cann't hold params
 */
class TaskViewModelFactory(val taskRepository: TaskRepository) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TaskViewModel(taskRepository) as T
    }
}