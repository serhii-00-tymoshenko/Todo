package com.mintokoneko.todo.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mintokoneko.todo.ui.main.view_model.MainViewModel
import com.mintokoneko.todo.ui.profile.view_model.ProfileViewModel
import com.mintokoneko.todo.ui.tasks.deleted_tasks.view_model.DeletedTasksViewModel
import com.mintokoneko.todo.ui.tasks.done_tasks.view_model.DoneTasksViewModel
import com.mintokoneko.todo.ui.tasks.in_progress_tasks.view_model.InProgressTasksViewModel

class BaseViewModelFactory(
    private val repository: BaseRepository,
) : ViewModelProvider.Factory {
    private inline fun <reified R> BaseRepository.cast(): R = this as R  // Casts baseRepository

    override fun <T : ViewModel> create(modelClass: Class<T>): T = when {
        modelClass.isAssignableFrom(DoneTasksViewModel::class.java) -> DoneTasksViewModel(
            repository.cast()
        )

        modelClass.isAssignableFrom(InProgressTasksViewModel::class.java) -> InProgressTasksViewModel(
            repository.cast()
        )

        modelClass.isAssignableFrom(DeletedTasksViewModel::class.java) -> DeletedTasksViewModel(
            repository.cast()
        )

        modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(
            repository.cast()
        )

        modelClass.isAssignableFrom(ProfileViewModel::class.java) -> ProfileViewModel(
            repository.cast()
        )

        else -> throw IllegalArgumentException("Wrong viewModel")
    } as T
}