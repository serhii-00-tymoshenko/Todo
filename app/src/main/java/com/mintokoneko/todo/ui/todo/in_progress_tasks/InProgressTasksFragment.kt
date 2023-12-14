package com.mintokoneko.todo.ui.todo.in_progress_tasks

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mintokoneko.todo.adapters.TaskAdapter
import com.mintokoneko.todo.base.BaseViewModelProvider
import com.mintokoneko.todo.databinding.FragmentInProgressTasksBinding
import com.mintokoneko.todo.repositories.TasksRepository
import com.mintokoneko.todo.ui.todo.in_progress_tasks.view_model.InProgressTasksViewModel


class InProgressTasksFragment : Fragment() {
    private var _binding: FragmentInProgressTasksBinding? = null
    private val binding get() = _binding!!

    private lateinit var inProgressTasksViewModel: InProgressTasksViewModel
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInProgressTasksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = requireContext()
        initTasksViewModel(this, context)
        setupRecyclers(context)
        initObservers()
    }

    private fun initObservers() {
        inProgressTasksViewModel.inProgressTasks.observe(viewLifecycleOwner) { inProgressTasks ->
            taskAdapter.submitList(inProgressTasks)
        }
    }

    private fun setupRecyclers(context: Context) {
        setupInProgressTasksRecycler(context)
    }

    private fun setupInProgressTasksRecycler(context: Context) {
        taskAdapter = TaskAdapter()

        val inProgressTasksRecycler = binding.inProgressTasksRecycler
        inProgressTasksRecycler.apply {
            adapter = taskAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    private fun initTasksViewModel(fragment: Fragment, context: Context) {
        val tasksRepository = TasksRepository.getInstance(context)
        inProgressTasksViewModel =
            BaseViewModelProvider.getInstance().getViewModel(fragment, tasksRepository)
    }

    companion object {
        const val IN_PROGRESS_TASKS_FRAGMENT_TAG = "In progress tasks"
    }
}