package com.example.ptiapplicationv2.intro

import android.os.Bundle
import android.view.View
import com.example.ptiapplicationv2.core.CorePtiFragment
import com.example.ptiapplicationv2.databinding.FragmentIntroBinding

class IntroFragment : CorePtiFragment<FragmentIntroBinding>(FragmentIntroBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO init view
    }
}