package com.mintokoneko.todo.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mintokoneko.todo.ui.todo.deletedtasks.DeletedTasksFragment
import com.mintokoneko.todo.ui.todo.donetasks.DoneTasksFragment
import com.mintokoneko.todo.ui.todo.inprogresstasks.InProgressTasksFragment

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