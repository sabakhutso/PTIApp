package com.example.ptiapplicationv2.presentation.caclucatedate

import android.content.Context
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.NavController
import com.example.ptiapplicationv2.databinding.FragmentCalculateDateBinding
import com.example.ptiapplicationv2.domain.model.CalculateDeadLineResult.SagencyResultDomain.ErrorDomain
import com.example.ptiapplicationv2.domain.model.CalculateDeadLineResult.SagencyResultDomain.SagencyResult

interface CalculateDateEvent {
    fun apply(navController: NavController, context: Context, binding: FragmentCalculateDateBinding)

    data class NavigateToDetails(
        private val successData: SagencyResult
    ) : CalculateDateEvent {
        override fun apply(
            navController: NavController,
            context: Context,
            binding: FragmentCalculateDateBinding
        ) {
            binding.errorText.isVisible = false
            Toast.makeText(context, "Success navigation", Toast.LENGTH_LONG).show()
        }
    }

    data class NavigateToErrorPage(
        private val errorDomain: ErrorDomain
    ) : CalculateDateEvent {
        override fun apply(
            navController: NavController,
            context: Context,
            binding: FragmentCalculateDateBinding
        ) {
            binding.errorText.isVisible = false
            Toast.makeText(context, "Error navigation", Toast.LENGTH_LONG).show()
        }
    }

    data object ShowErrorToast : CalculateDateEvent {
        override fun apply(
            navController: NavController,
            context: Context,
            binding: FragmentCalculateDateBinding
        ) {
            binding.errorText.isVisible = true
            Toast.makeText(
                context,
                "Service error, or internet connection error",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
