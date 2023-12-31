package com.mintokoneko.todo.ui.todo.deletedtasks

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mintokoneko.todo.adapters.TaskAdapter
import com.mintokoneko.todo.base.BaseViewModelProvider
import com.mintokoneko.todo.databinding.FragmentDeletedTasksBinding
import com.mintokoneko.todo.repositories.TasksRepository
import com.mintokoneko.todo.ui.todo.deletedtasks.viewmodel.DeletedTasksViewModel


class DeletedTasksFragment : Fragment() {
    private var _binding: FragmentDeletedTasksBinding? = null
    private val binding get() = _binding!!

    private lateinit var deletedTasksViewModel: DeletedTasksViewModel
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeletedTasksBinding.inflate(inflater, container, false)
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
        deletedTasksViewModel =
            BaseViewModelProvider.getInstance().getViewModel(fragment, tasksRepository)
    }

    private fun setupRecyclers(context: Context) {
        setupDeletedTasksRecycler(context)
    }

    private fun setupDeletedTasksRecycler(context: Context) {
        taskAdapter = TaskAdapter()

        val deletedTasksRecycler = binding.deletedTasksRecycler
        deletedTasksRecycler.apply {
            adapter = taskAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    private fun initObservers() {
        deletedTasksViewModel.deletedTasks.observe(viewLifecycleOwner) { deletedTasks ->
            taskAdapter.submitList(deletedTasks)
        }
    }

    companion object {
        const val DELETED_TASKS_FRAGMENT_TAG = "Deleted tasks"
    }
}