package com.example.ptiapplicationv2.presentation.caclucatedate

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.example.ptiapplicationv2.domain.model.CalculateDeadLineResult.SagencyResultDomain.ErrorDomain
import com.example.ptiapplicationv2.domain.model.CalculateDeadLineResult.SagencyResultDomain.SagencyResult

interface CalculateDateEvent {

    fun apply(navController: NavController, context: Context)

    data class NavigateToDetails(
        private val successData: SagencyResult
    ) : CalculateDateEvent {
        override fun apply(navController: NavController, context: Context) {
            Toast.makeText(context, "Success navigation", Toast.LENGTH_LONG).show()
        }
    }

    data class NavigateToErrorPage(
        private val errorDomain: ErrorDomain
    ) : CalculateDateEvent {
        override fun apply(navController: NavController, context: Context) {
            Toast.makeText(context, "Error navigation", Toast.LENGTH_LONG).show()
        }
    }

    data object ShowErrorToast : CalculateDateEvent {
        override fun apply(navController: NavController, context: Context) {
            Toast.makeText(
                context,
                "Service error, or internet connection error",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
