package com.example.androidsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var operations: Operations
    private var num1 = 0
    private var num2 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        operations = Operations()
    }

    fun convertCurrency(view : View){
        if(number1.text.isNotEmpty()){
            val dollarValue = number1.text.toString().toFloat()
            val euroVaue = dollarValue * 0.85f
            textView.text = euroVaue.toString()
        } else {
            textView.text = getString(R.string.no_value_string)
        }
    }

    fun addOpe(view: View){
        if(validityInputs()){
            num1 = number1.text.trim().toString().toInt()
            num2 = number2.text.trim().toString().toInt()
            textView.text = operations.add(num1, num2).toString()
        } else {
            textView.text = getString(R.string.no_value_string)
        }
    }

    fun substractOpe(view: View){
        if(validityInputs()){
            num1 = number1.text.trim().toString().toInt()
            num2 = number2.text.trim().toString().toInt()
            textView.text = operations.subtract(num1, num2).toString()
        } else {
            textView.text = getString(R.string.no_value_string)
        }
    }

    fun multiOpe(view: View){
        if(validityInputs()){
            num1 = number1.text.trim().toString().toInt()
            num2 = number2.text.trim().toString().toInt()
            textView.text = operations.multiply(num1, num2).toString()
        } else {
            textView.text = getString(R.string.no_value_string)
        }
    }

    fun divideOpe(view: View){
        if(validityInputs()){
            num1 = number1.text.trim().toString().toInt()
            num2 = number2.text.trim().toString().toInt()
            textView.text = operations.divide(num1, num2).toString()
        } else {
            textView.text = getString(R.string.no_value_string)
        }
    }

    fun validityInputs(): Boolean{
        if(number1.text.toString().isNotEmpty() && number2.text.toString().isNotEmpty()){
            return true
        }
        return false
    }
}