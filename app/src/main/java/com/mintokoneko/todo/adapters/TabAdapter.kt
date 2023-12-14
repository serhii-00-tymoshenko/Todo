package com.mintokoneko.todo.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mintokoneko.todo.ui.todo.deleted_tasks.DeletedTasksFragment
import com.mintokoneko.todo.ui.todo.done_tasks.DoneTasksFragment
import com.mintokoneko.todo.ui.todo.in_progress_tasks.InProgressTasksFragment

class TabAdapter(fragmentActivity: FragmentActivity, private val size: Int) :
    FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> InProgressTasksFragment()
            1 -> DoneTasksFragment()
            2 -> DeletedTasksFragment()
            else -> throw IllegalArgumentException("Fragment not found")
        }
    }

    override fun getItemCount() = size
}