package com.example.ptiapplicationv2.intro

import android.os.Bundle
import android.view.View
import com.example.ptiapplicationv2.R
import androidx.navigation.fragment.findNavController
import com.example.ptiapplicationv2.core.CorePtiFragment
import com.example.ptiapplicationv2.databinding.FragmentIntroBinding

class IntroFragment : CorePtiFragment<FragmentIntroBinding>(FragmentIntroBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.calculateDateButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_ptiIntroFragment_to_calculateDateFragment
            )
        }
        binding.visitButton.setOnClickListener {
            // TODO navigate to different page
        }
    }
}