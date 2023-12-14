package com.mintokoneko.todo.ui.todo.donetasks

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mintokoneko.todo.adapters.TaskAdapter
import com.mintokoneko.todo.base.BaseViewModelProvider
import com.mintokoneko.todo.databinding.FragmentDoneTasksBinding
import com.mintokoneko.todo.repositories.TasksRepository
import com.mintokoneko.todo.ui.todo.donetasks.viewmodel.DoneTasksViewModel

class DoneTasksFragment : Fragment() {
    private var _binding: FragmentDoneTasksBinding? = null
    private val binding get() = _binding!!

    private lateinit var doneTasksViewModel: DoneTasksViewModel
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoneTasksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = requireContext()
        initTasksViewModel(this, context)
        setupRecyclers(context)
        initObservers()
    }

    private fun initTasksViewModel(fragment: Fragment, context: Context) {
        val tasksRepository = TasksRepository.getInstance(context)
        doneTasksViewModel =
            BaseViewModelProvider.getInstance().getViewModel(fragment, tasksRepository)
    }

    private fun setupRecyclers(context: Context) {
        setupDoneTasksRecycler(context)
    }

    private fun setupDoneTasksRecycler(context: Context) {
        taskAdapter = TaskAdapter()

        val doneTasksRecycler = binding.doneTasksRecycler
        doneTasksRecycler.apply {
            adapter = taskAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    private fun initObservers() {
        doneTasksViewModel.doneTasks.observe(viewLifecycleOwner) { doneTasks ->
            taskAdapter.submitList(doneTasks)
        }
    }

    companion object {
        const val DONE_TASKS_FRAGMENT_TAG = "Done tasks"
    }
}