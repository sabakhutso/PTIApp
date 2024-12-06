package com.example.ptiapplicationv2.presentation.user_authorization

import android.os.Bundle
import android.view.View
import com.example.ptiapplicationv2.presentation.core.CorePtiFragment
import com.example.ptiapplicationv2.databinding.FragmentUserAuthorizationBinding

class AuthorizationFragment :
    CorePtiFragment<FragmentUserAuthorizationBinding>(FragmentUserAuthorizationBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO auth page
    }
}
