package com.example.to_doapp

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.to_doapp.databinding.FragmentAddEditBinding

class AddEditFragment : Fragment() {
    private val taskViewModel: TaskViewModel by activityViewModels()
    private var taskPosition: Int? = null
    private lateinit var binding: FragmentAddEditBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        taskPosition = arguments?.getInt("position")
        val taskTitle = arguments?.getString("title")
        val taskDescription = arguments?.getString("description")
        binding.titleInput.setText(taskTitle)
        binding.descriptionInput.setText(taskDescription)

        binding.buttonSave.setOnClickListener {
            val title = binding.titleInput.text.toString().trim()
            val description = binding.descriptionInput.text.toString().trim()

            if (validateInputs(title, description)) {
                val updatedTask = Task(title, description)

                if (taskPosition != null) {
                    taskViewModel.updateTask(taskPosition!!, updatedTask)
                } else {
                    taskViewModel.addTask(updatedTask)
                }

                findNavController().popBackStack()
            }
        }
    }

    private fun validateInputs(title: String, description: String): Boolean {
        var isValid = true

        if (title.isEmpty()) {
            binding.titleInputLayout.error = "Title is required"
            isValid = false
        } else {
            binding.titleInputLayout.error = null
        }

        if (description.isEmpty()) {
            binding.descriptionInputLayout.error = "Description is required"
            isValid = false
        } else {
            binding.descriptionInputLayout.error = null
        }

        if (!isValid) {
            Toast.makeText(requireContext(), "Please fix the errors", Toast.LENGTH_SHORT).show()
        }

        return isValid
    }
}