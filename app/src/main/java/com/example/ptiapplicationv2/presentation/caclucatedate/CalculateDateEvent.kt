package com.example.ptiapplicationv2.presentation.caclucatedate

import android.content.Context
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.NavController
import com.example.ptiapplicationv2.databinding.FragmentCalculateDateBinding
import com.example.ptiapplicationv2.domain.model.CalculateDeadLineResult.SagencyResultDomain.ErrorDomain
import com.example.ptiapplicationv2.domain.model.CalculateDeadLineResult.SagencyResultDomain.SagencyResult

// TODO needs to be refactored strings and in general..
interface CalculateDateEvent {

    fun apply(navController: NavController, context: Context, binding: FragmentCalculateDateBinding)

    abstract class AbstractCalculateDate(private val errorVisible: Boolean) : CalculateDateEvent {
        override fun apply(
            navController: NavController,
            context: Context,
            binding: FragmentCalculateDateBinding
        ) {
            binding.errorText.isVisible = errorVisible
        }
    }

    data class NavigateToDetails(
        private val successData: SagencyResult
    ) : AbstractCalculateDate(errorVisible = false) {
        override fun apply(
            navController: NavController,
            context: Context,
            binding: FragmentCalculateDateBinding
        ) {
            super.apply(navController, context, binding)
            Toast.makeText(context, "Success navigation", Toast.LENGTH_LONG).show()
        }
    }

    data class NavigateToErrorPage(
        private val errorDomain: ErrorDomain
    ) : AbstractCalculateDate(errorVisible = false) {
        override fun apply(
            navController: NavController,
            context: Context,
            binding: FragmentCalculateDateBinding
        ) {
            super.apply(navController, context, binding)
            Toast.makeText(context, "Error navigation", Toast.LENGTH_LONG).show()
        }
    }

    data object ShowErrorToast : AbstractCalculateDate(errorVisible = true) {
        override fun apply(
            navController: NavController,
            context: Context,
            binding: FragmentCalculateDateBinding
        ) {
            super.apply(navController, context, binding)
            Toast.makeText(
                context,
                "Service error, or internet connection error",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    data object NumberInputError : AbstractCalculateDate(errorVisible = false) {
        override fun apply(
            navController: NavController,
            context: Context,
            binding: FragmentCalculateDateBinding
        ) {
            super.apply(navController, context, binding)
            binding.governmentCarInput.error = "შეავსე ველი"
        }
    }

    data object CategoryInputError : AbstractCalculateDate(errorVisible = false) {
        override fun apply(
            navController: NavController,
            context: Context,
            binding: FragmentCalculateDateBinding
        ) {
            super.apply(navController, context, binding)
            binding.vehicleCategory.error = "შეავსე კატეგორია"
        }
    }

    data object ShowDualInputError : AbstractCalculateDate(errorVisible = false) {
        override fun apply(
            navController: NavController,
            context: Context,
            binding: FragmentCalculateDateBinding
        ) {
            super.apply(navController, context, binding)
            binding.governmentCarInput.error = "შეავსე ველი"
            binding.vehicleCategory.error = "შეავსე კატეგორია"
        }
    }
}
