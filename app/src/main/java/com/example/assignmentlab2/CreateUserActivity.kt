package com.example.assignmentlab2

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.assignmentlab2.databinding.ActivityCreateuserBinding

class CreateUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateuserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreateuserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val users = intent.getSerializableExtra("users") as Array<User>

        binding.buttonCreate.setOnClickListener {
            val firstName = binding.editTextTextFirstName.text.toString()
            val lastName = binding.editTextTextLastName.text.toString()
            val username = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()

            if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || password.isEmpty()) {
                showMsg("Fields cannot be empty")
            } else if (!Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
                showMsg("Invalid Email Address")
            } else {
                var tempUser = users.findLast {
                    it.userName == username
                }
                if (tempUser == null) {
                    val user = User(firstName, lastName, username, password)
                    val newIntent = intent
                    newIntent.putExtra("user", user)
                    setResult(Activity.RESULT_OK, newIntent)
                    finish()
                } else {
                    showMsg("UserName already exists")
                }
            }
        }
    }


    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        setResult(Activity.RESULT_CANCELED)
        finish()
    }

    private fun showMsg(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}