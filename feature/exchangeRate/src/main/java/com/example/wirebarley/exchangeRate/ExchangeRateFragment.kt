package com.example.wirebarley.exchangeRate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.wirebarley.feature.exchangeRate.R
import com.example.wirebarley.feature.exchangeRate.databinding.FragmentExchangeRateBinding

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
            viewModel.exchangeRateUiState.collect{
                binding.apply {
                    from.text = it.from.name.withCurrencyFormat(it.from.currency)
                    to.text = it.to.name.withCurrencyFormat(it.to.currency)
                    exchangeRate.text = it.exchangeRate.toCurrencyFromToDecimalFormat(
                        from = it.from.currency,
                        to = it.to.currency
                    )
                    result.text = getString(R.string.result,(it.exchangeRate * it.remittance).toSecondDecimalPlaceFormat(),it.to.currency)
                }
            }
        }
    }

    private fun setListner() {
        binding.apply {
            editText.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    viewModel.updateRemittance(editText.text.toString())
                    Toast.makeText(requireContext(), editText.text, Toast.LENGTH_SHORT).show();
                }
                false
            }
        }
    }

    private fun setPicker() {
        binding.apply {
            picker.minValue = 0
            picker.maxValue = viewModel.countryList.size - 1
            picker.displayedValues = viewModel.countryList.map { "${it.name} (${it.currency})" }.toTypedArray()
            picker.setOnValueChangedListener { numberPicker, _, _ ->
                viewModel.updateSelected(viewModel.countryList[numberPicker.value])
            }
        }
    }

}