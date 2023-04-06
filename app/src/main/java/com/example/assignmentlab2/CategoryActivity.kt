package com.example.assignmentlab2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.assignmentlab2.databinding.ActivityCategoryBinding

class CategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryBinding
    lateinit var result: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Get User from previous intent
        result = intent.getSerializableExtra("user") as User

        //update User Email
        binding.textViewWelcomeUser.text = "Welcome " + result.userName

        //Titles for categories
        val displays = arrayOf(binding.textViewCategory0.text.toString(),
            binding.textViewCategory1.text.toString(),
            binding.textViewCategory2.text.toString(),
            binding.textViewCategory3.text.toString())

        //On Click Listener for Categories
        arrayOf(binding.imageViewCategory0, binding.imageViewCategory1, binding.imageViewCategory2, binding.imageViewCategory3).forEach {
            it.setOnClickListener {
                Toast.makeText(this, displays[it.tag.toString().toInt()], Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonViewCalc.setOnClickListener {
            startActivity(Intent(this, CalcActivity::class.java))
        }

    }
}