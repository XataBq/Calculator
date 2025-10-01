package com.example.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null

    private var result = ""
    private var flagDouble = false
    private var resultField: String = ""
    private var numberField: String = ""
    private val binding
        get() = _binding ?: throw IllegalStateException("ActivityMainBinding can't be null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        with(binding) {
            btnAdd.setOnClickListener { onOperationClick(btnAdd.text.toString()) }
            btnDiv.setOnClickListener { onOperationClick(btnDiv.text.toString()) }
            btnMul.setOnClickListener { onOperationClick(btnMul.text.toString()) }
            btnSub.setOnClickListener { onOperationClick(btnSub.text.toString()) }
            btnComma.setOnClickListener { onCommaClick() }
            btnEq.setOnClickListener { onEqualsClick() }
            btnN0.setOnClickListener { onNumberClick(btnN0.text.toString()) }
            btnN1.setOnClickListener { onNumberClick(btnN1.text.toString()) }
            btnN2.setOnClickListener { onNumberClick(btnN2.text.toString()) }
            btnN3.setOnClickListener { onNumberClick(btnN3.text.toString()) }
            btnN4.setOnClickListener { onNumberClick(btnN4.text.toString()) }
            btnN5.setOnClickListener { onNumberClick(btnN5.text.toString()) }
            btnN6.setOnClickListener { onNumberClick(btnN6.text.toString()) }
            btnN7.setOnClickListener { onNumberClick(btnN7.text.toString()) }
            btnN8.setOnClickListener { onNumberClick(btnN8.text.toString()) }
            btnN9.setOnClickListener { onNumberClick(btnN9.text.toString()) }
            btnClearResult.setOnClickListener { onClearResult() }
        }
    }

    private fun onOperationClick(operation: String) {
        binding.tvOperationFiled.text = operation
        if (binding.etNumberField.text.isNotEmpty()) binding.tvResultField.text =
            binding.etNumberField.text
        binding.etNumberField.text.clear()
        flagDouble = false
    }

    private fun onCommaClick() {
        if (binding.etNumberField.text.isNotEmpty() && !flagDouble) {
            flagDouble = true
            binding.etNumberField.append(",")
        }
    }

    private fun onNumberClick(number: String) {
        binding.etNumberField.append(number)
    }

    private fun onEqualsClick() {
        resultField = binding.tvResultField.text.toString().replace(",", ".")
        numberField = binding.etNumberField.text.toString().replace(",", ".")
        result = if (resultField.isEmpty() && numberField.isEmpty()) "0"
        else if (resultField.isEmpty()) numberField
        else if (numberField.isEmpty()) resultField
        else {
            if (numberField.toDouble() == 0.0) {
                Toast.makeText(
                    this@MainActivity,
                    "You can't divide by zero!!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            performOperation(
                resultField,
                numberField,
                binding.tvOperationFiled.text.toString()
            ).toString()
        }
        binding.tvResultField.text = result.replace(",", ".")
        flagDouble = false
        binding.etNumberField.text.clear()
    }

    private fun performOperation(num1: String, num2: String, operator: String): Double {
        return when (operator) {
            Operators.MULTIPLY.text -> num1.toDouble() * num2.toDouble()
            Operators.DIVIDE.text -> num1.toDouble() / num2.toDouble()
            Operators.ADDITION.text -> num1.toDouble() + num2.toDouble()
            Operators.SUBTRACTION.text -> num1.toDouble() - num2.toDouble()
            else -> 0.0
        }

    }

    private fun onClearResult() {
        binding.tvResultField.text = ""
    }

}