package com.example.ptiapplicationv2.presentation.caclucatedate

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.example.ptiapplicationv2.presentation.core.CorePtiFragment
import com.example.ptiapplicationv2.databinding.FragmentCalculateDateBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CalculateDateFragment :
    CorePtiFragment<FragmentCalculateDateBinding>(FragmentCalculateDateBinding::inflate) {

    private val viewModel by viewModels<CalculateDateViewModel>()

    // TODO items should be provided from service and mapped on ui correspondingly
    private val items = listOf(
        "ავტომობილის კატეგორია",
        "M1 - არაუმეტეს 8 ადგილისა",
        "M2 - ავტობუსი 5ტ-მდე",
        "M2 - ავტობუსი 5ტ-მდე"
    )

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.vehicleCategory.adapter = adapter
        viewModel.init()
    }
}