package com.example.ptiapplicationv2.presentation.intro

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.ptiapplicationv2.databinding.FragmentIntroBinding
import com.example.ptiapplicationv2.presentation.core.CorePtiFragment
import kotlinx.coroutines.launch

class IntroFragment : CorePtiFragment<FragmentIntroBinding>(FragmentIntroBinding::inflate) {

    private val viewModel: IntroViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        calculateDateButton.setOnClickListener { viewModel.openCalculateDatePage() }
        visitButton.setOnClickListener { viewModel.openAuthorizationPage() }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.actionObserver.flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collect { event -> event.apply(findNavController()) }
        }
    }
}