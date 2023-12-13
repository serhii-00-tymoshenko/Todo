package com.mintokoneko.todo.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mintokoneko.todo.ui.tasks.deleted_tasks.DeletedTasksFragment
import com.mintokoneko.todo.ui.tasks.done_tasks.DoneTasksFragment
import com.mintokoneko.todo.ui.tasks.in_progress_tasks.InProgressTasksFragment
import java.lang.IllegalArgumentException

class TabAdapter(fragmentActivity: FragmentActivity, private val size: Int) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = size

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> InProgressTasksFragment.newInstance()
            1 -> DoneTasksFragment.newInstance()
            2 -> DeletedTasksFragment.newInstance()
            else -> throw IllegalArgumentException("Fragment not found")
        }
    }
}