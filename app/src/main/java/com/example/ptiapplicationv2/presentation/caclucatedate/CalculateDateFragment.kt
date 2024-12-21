package com.example.ptiapplicationv2.presentation.caclucatedate

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.ptiapplicationv2.presentation.core.CorePtiFragment
import com.example.ptiapplicationv2.databinding.FragmentCalculateDateBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CalculateDateFragment :
    CorePtiFragment<FragmentCalculateDateBinding>(FragmentCalculateDateBinding::inflate) {

    private val viewModel by viewModels<CalculateDateViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        visitButton.setOnClickListener {
            val currentNumber = governmentCarInput.text.toString()
            val categoryInput = vehicleCategory.text.toString()
            viewModel.calculateDeadLine(
                cardNumber = currentNumber,
                category = categoryInput
            )
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.events.flowWithLifecycle(lifecycle = viewLifecycleOwner.lifecycle)
                .collect { event ->
                    event.apply(
                        navController = findNavController(),
                        context = requireContext(),
                        binding = binding
                    )
                }
        }
    }
}