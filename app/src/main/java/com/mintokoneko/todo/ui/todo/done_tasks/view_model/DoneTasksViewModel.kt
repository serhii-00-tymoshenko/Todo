package com.mintokoneko.todo.ui.todo.done_tasks.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mintokoneko.todo.base.BaseViewModel
import com.mintokoneko.todo.data.Task
import com.mintokoneko.todo.repositories.TasksRepository

class DoneTasksViewModel(private val tasksRepository: TasksRepository) : BaseViewModel() {
    private val _doneTasks = MutableLiveData<List<Task>>()
    val doneTasks: LiveData<List<Task>> get() = _doneTasks

    init {
        getDoneTasks().also { doneTasks -> setDoneTasks(doneTasks) }
    }

    private fun getDoneTasks() = tasksRepository.getDoneTasks()

    private fun setDoneTasks(tasks: List<Task>) {
        _doneTasks.value = tasks
    }
}