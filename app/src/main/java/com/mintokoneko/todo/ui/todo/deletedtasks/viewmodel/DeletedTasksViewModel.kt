package com.mintokoneko.todo.ui.todo.deletedtasks.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mintokoneko.todo.base.BaseViewModel
import com.mintokoneko.todo.data.Task
import com.mintokoneko.todo.repositories.TasksRepository


class DeletedTasksViewModel(
    private val tasksRepository: TasksRepository
) : BaseViewModel() {
    private val _deletedTasks = MutableLiveData<List<Task>>()
    val deletedTasks: LiveData<List<Task>> get() = _deletedTasks

    init {
        getDeletedTasks().also { deletedTasks -> seDeletedTasks(deletedTasks) }
    }

    private fun getDeletedTasks() = tasksRepository.getDeletedTasks()

    private fun seDeletedTasks(tasks: List<Task>) {
        _deletedTasks.value = tasks
    }
}