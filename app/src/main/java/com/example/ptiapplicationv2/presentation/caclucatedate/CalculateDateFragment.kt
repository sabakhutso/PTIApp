package com.example.ptiapplicationv2.presentation.caclucatedate

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
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

    private val items = listOf(
        "ავტომობილის კატეგორია",
        "M1 - არაუმეტეს 8 ადგილისა",
        "M2 - ავტობუსი 5ტ-მდე",
        "M2 - ავტობუსი 5ტ-მდე"
    )

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        vehicleCategory.adapter = adapter
        visitButton.setOnClickListener {
            val currentNumber = governmentInput.text.toString()
            viewModel.calculateDeadLine(cardNumber = currentNumber)
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.events.flowWithLifecycle(
                lifecycle = viewLifecycleOwner.lifecycle
            ).collect { event ->
                event.apply(
                    navController = findNavController(),
                    context = requireContext()
                )
            }
        }
    }
}