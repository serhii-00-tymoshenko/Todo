package com.mintokoneko.todo.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mintokoneko.todo.ui.main.view_model.MainViewModel
import com.mintokoneko.todo.ui.profile.view_model.ProfileViewModel
import com.mintokoneko.todo.ui.tasks.deleted_tasks.view_model.DeletedTasksViewModel
import com.mintokoneko.todo.ui.tasks.done_tasks.view_model.DoneTasksViewModel
import com.mintokoneko.todo.ui.tasks.in_progress_tasks.view_model.InProgressTasksViewModel

class BaseViewModelFactory(
    private val baseRepository: BaseRepository,
) : ViewModelProvider.Factory {
    // Casts baseRepository
    private inline fun <reified R> getCorrectRepository(): R = baseRepository as R

    override fun <T : ViewModel> create(modelClass: Class<T>): T = when {
        modelClass.isAssignableFrom(DoneTasksViewModel::class.java) -> DoneTasksViewModel(
            getCorrectRepository()
        )

        modelClass.isAssignableFrom(InProgressTasksViewModel::class.java) -> InProgressTasksViewModel(
            getCorrectRepository()
        )

        modelClass.isAssignableFrom(DeletedTasksViewModel::class.java) -> DeletedTasksViewModel(
            getCorrectRepository()
        )

        modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(
            getCorrectRepository()
        )

        modelClass.isAssignableFrom(ProfileViewModel::class.java) -> ProfileViewModel(
            getCorrectRepository()
        )

        else -> throw IllegalArgumentException("Wrong viewModel")
    } as T
}