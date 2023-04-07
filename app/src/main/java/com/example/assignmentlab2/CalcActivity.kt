package com.example.assignmentlab2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.assignmentlab2.databinding.ActivityCalcBinding

class CalcActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalcBinding

    private val number1: Int
    get() {
        return (binding.editTextNumber.text.toString().toIntOrNull() ?: 0)
    }

    private val number2: Int
    get() {
        return (binding.editTextNumber2.text.toString().toIntOrNull() ?: 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCalcBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //this is a plus button
        binding.buttonPlus.setOnClickListener {
            showMsg( "$number1+$number2 = ${(number1 + number2)}")
        }

        //this is a minus button
        binding.buttonMinus.setOnClickListener {
            showMsg( "$number1-$number2 = ${(number1 - number2)}")
        }

        //this is a * button
        binding.buttonMulti.setOnClickListener {
            showMsg( "$number1*$number2 = ${(number1*number2)}")
        }


        //this is a / button
        binding.buttonDiv.setOnClickListener {
            showMsg( "$number1/$number2 = ${(number1 / number2)}")
        }

    }

    fun showMsg(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        binding.textViewResultValue.text = msg
    }
}