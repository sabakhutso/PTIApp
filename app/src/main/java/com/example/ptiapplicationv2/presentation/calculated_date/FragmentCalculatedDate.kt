package com.example.ptiapplicationv2.presentation.calculated_date

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.ptiapplicationv2.R
import com.example.ptiapplicationv2.databinding.FragmentCalculatedDateBinding
import com.example.ptiapplicationv2.presentation.core.CorePtiFragment
import com.example.ptiapplicationv2.presentation.intro.IntroViewModel
import kotlinx.coroutines.launch


class FragmentCalculatedDate :
    CorePtiFragment<FragmentCalculatedDateBinding>(FragmentCalculatedDateBinding::inflate) {
    private val viewModel: CalculatedDateViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        xButton.setOnClickListener {findNavController().navigate(R.id.action_calculatedDateFragment_to_ptiIntroFragment) }
        visitButton.setOnClickListener {findNavController().navigate(R.id.action_calculatedDateFragment_to_authorizationFragment)}
    }
}