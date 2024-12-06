package com.example.ptiapplicationv2.presentation.intro

import androidx.navigation.NavController
import com.example.ptiapplicationv2.R

interface IntroUiAction {

    fun apply(navController: NavController)

    abstract class AbstractIntroUiAction(
        private val id: Int
    ) : IntroUiAction {
        override fun apply(navController: NavController) {
            navController.navigate(id)
        }
    }

    data object CalculateDatePage : AbstractIntroUiAction(
        R.id.action_ptiIntroFragment_to_calculateDateFragment
    )

    data object AuthorizationPage : AbstractIntroUiAction(
        R.id.action_ptiIntroFragment_to_authorizationFragment
    )
}
