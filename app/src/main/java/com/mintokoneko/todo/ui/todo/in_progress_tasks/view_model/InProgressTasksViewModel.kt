package com.mintokoneko.todo.ui.todo.in_progress_tasks.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mintokoneko.todo.base.BaseViewModel
import com.mintokoneko.todo.data.Task
import com.mintokoneko.todo.repositories.TasksRepository


class InProgressTasksViewModel(
    private val tasksRepository: TasksRepository
) : BaseViewModel() {
    private val _inProgressTasks = MutableLiveData<List<Task>>()
    val inProgressTasks: LiveData<List<Task>> get() = _inProgressTasks

    init {
        getInProgressTasks().also { inProgressTasks -> setInProgressTasks(inProgressTasks) }
    }

    private fun getInProgressTasks() = tasksRepository.getInProgressTasks()

    private fun setInProgressTasks(tasks: List<Task>) {
        _inProgressTasks.value = tasks
    }
}