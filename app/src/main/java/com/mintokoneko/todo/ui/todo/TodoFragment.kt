package com.mintokoneko.todo.ui.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mintokoneko.todo.adapters.TabAdapter
import com.mintokoneko.todo.databinding.FragmentTodoBinding

class TodoFragment : Fragment() {
    private var _binding: FragmentTodoBinding? = null
    private val binding get() = _binding!!

    private lateinit var tabAdapter: TabAdapter

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
        _binding = FragmentTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentActivity = requireActivity()
        setupContent(fragmentActivity)
    }

    private fun setupContent(fragmentActivity: FragmentActivity) {
        tabAdapter = TabAdapter(fragmentActivity, tabNames.size)
        val tabLayout = binding.tabLayout
        val tabsPager = binding.tabsPager

        setupTabsPager(tabsPager)
        setupTabMediator(tabLayout, tabsPager)
    }

    private fun setupTabsPager(tabsPager: ViewPager2) {
        tabsPager.adapter = tabAdapter
    }

    private fun setupTabMediator(tabLayout: TabLayout, tabsPager: ViewPager2) {
        TabLayoutMediator(tabLayout, tabsPager) { tab, position ->
            tab.text = tabNames[position]
        }.attach()
    }

    companion object {
        const val TODO_FRAGMENT_TAG = "Todo"
    }
}