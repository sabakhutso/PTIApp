package com.example.ptiapplicationv2.presentation.intro

import android.os.Bundle
import android.view.View
import com.example.ptiapplicationv2.R
import androidx.navigation.fragment.findNavController
import com.example.ptiapplicationv2.presentation.core.CorePtiFragment
import com.example.ptiapplicationv2.databinding.FragmentIntroBinding

class IntroFragment : CorePtiFragment<FragmentIntroBinding>(FragmentIntroBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        calculateDateButton.setOnClickListener {
            findNavController().navigate(R.id.action_ptiIntroFragment_to_calculateDateFragment)
        }
        visitButton.setOnClickListener {
            findNavController().navigate(R.id.action_ptiIntroFragment_to_authorizationFragment)
        }
    }
}