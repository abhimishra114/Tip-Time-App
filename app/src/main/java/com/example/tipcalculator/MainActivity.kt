package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            calculateTip()
        }
    }

    fun calculateTip() {
        val valueInTextField = binding.editText.text.toString()
        val cost = valueInTextField.toDoubleOrNull()
        if (cost == null) return
        val selectedId = binding.radioGroup.checkedRadioButtonId
        val tipPercentage: Double = when(selectedId){
            R.id.radio_button_1 -> 0.20
            R.id.radio_button_2 -> 0.18
            else -> 0.15
        }
        var tip = cost * tipPercentage
        val roundUp = binding.roundUpSwitch.isChecked
        if (roundUp){
            tip = kotlin.math.ceil(tip)
        }
        NumberFormat.getCurrencyInstance()
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount,formattedTip)
    }
}