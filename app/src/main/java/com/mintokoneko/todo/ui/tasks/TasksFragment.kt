package com.mintokoneko.todo.ui.tasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mintokoneko.todo.adapters.TabAdapter
import com.mintokoneko.todo.databinding.FragmentTasksBinding

class TasksFragment : Fragment() {
    private var _binding: FragmentTasksBinding? = null
    private val binding get() = _binding!!

    private lateinit var tabAdapter: TabAdapter
    private lateinit var tabsPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    private val tabNames =
        listOf(
            "In Progress",
            "Done",
            "Deleted"
        )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentActivity = requireActivity()

        setupContent(fragmentActivity)
    }

    private fun setupContent(fragmentActivity: FragmentActivity) {
        tabAdapter = TabAdapter(fragmentActivity, tabNames.size)
        tabLayout = binding.tabLayout
        tabsPager = binding.tabsPager

        setupTabsPager()
        setupTabMediator()
    }

    private fun setupTabMediator() {
        TabLayoutMediator(tabLayout, tabsPager) { tab, position ->
            tab.text = tabNames[position]
        }.attach()
    }

    private fun setupTabsPager() {
        tabsPager.adapter = tabAdapter
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            TasksFragment().apply {
                arguments = Bundle().apply { }
            }
    }
}