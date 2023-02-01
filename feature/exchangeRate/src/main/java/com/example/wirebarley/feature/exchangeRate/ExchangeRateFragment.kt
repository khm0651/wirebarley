package com.example.wirebarley.feature.exchangeRate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.wirebarley.core.common.extensions.toCurrencyFromToDecimalFormat
import com.example.wirebarley.core.common.extensions.toSecondDecimalPlaceFormat
import com.example.wirebarley.core.common.extensions.withCurrencyKrFormat
import com.example.wirebarley.feature.exchangeRate.R
import com.example.wirebarley.feature.exchangeRate.databinding.FragmentExchangeRateBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExchangeRateFragment : Fragment() {

    private lateinit var binding: FragmentExchangeRateBinding
    private val viewModel: ExchangeRateViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExchangeRateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPicker()
        setListner()
        setObserve()
    }

    private fun setObserve() {
        //data binding 형식으로 바꿀지 유무?..
        lifecycleScope.launchWhenCreated {
            viewModel.exchangeRateUi.collect{
                when(it){
                    is ExchangeRateUiState.Success -> {
                        val data = it.exchangeRate
                        binding.apply {
                            from.text = data.from.name.withCurrencyKrFormat(data.from.currency)
                            to.text = data.to.name.withCurrencyKrFormat(data.to.currency)
                            exchangeRate.text = data.exchangeRate.toCurrencyFromToDecimalFormat(
                                from = data.from.currency,
                                to = data.to.currency
                            )
                            requestTime.text = data.requestTime
                            result.text = getString(R.string.result,(data.exchangeRate * data.remittance).toSecondDecimalPlaceFormat(),data.to.currency)
                        }
                    }
                    ExchangeRateUiState.Error -> {}
                    ExchangeRateUiState.Loading -> {}
                }

            }
        }
    }

    private fun setListner() {
        binding.apply {
            editText.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if(editText.text.toString().toDouble() < 0 || editText.text.toString().toDouble() > 10000){
                        Toast.makeText(requireContext(), "송금액이 바르지 않습니다", Toast.LENGTH_SHORT).show();
                    }else{
                        viewModel.updateRemittance(editText.text.toString())
                    }
                }
                false
            }
        }
    }

    private fun setPicker() {
        binding.apply {
            picker.minValue = 0
            picker.maxValue = viewModel.countryInformationLists.size - 1
            picker.displayedValues = viewModel.countryInformationLists.map { "${it.name} (${it.currency})" }.toTypedArray()
            picker.setOnValueChangedListener { numberPicker, _, _ ->
                viewModel.updateSelected(viewModel.countryInformationLists[numberPicker.value])
            }
        }
    }

}