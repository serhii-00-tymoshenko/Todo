package com.mintokoneko.todo.repositories

import android.content.Context
import com.mintokoneko.todo.base.BaseRepository
import com.mintokoneko.todo.data.Task

class TasksRepository(context: Context) : BaseRepository(context) {
    fun getInProgressTasks(): List<Task> =
        listOf(
            Task("InProgress1", 0),
            Task("InProgress2", 1),
            Task("InProgress3", 2),
            Task("InProgress4", 3),
            Task("InProgress5", 4),
        )

    fun getDeletedTasks(): List<Task> =
        listOf(
            Task("Deleted1", 0),
            Task("Deleted2", 1),
            Task("Deleted3", 2),
            Task("Deleted4", 3),
            Task("Deleted5", 4),
        )

    fun getDoneTasks(): List<Task> =
        listOf(
            Task("Done1", 0),
            Task("Done2", 1),
            Task("Done3", 2),
            Task("Done4", 3),
            Task("Done5", 4),
        )

    companion object {
        @Volatile
        private var instance: TasksRepository? = null

        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: TasksRepository(context).also { instance = it }
            }
    }
}