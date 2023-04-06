package com.example.assignmentlab2

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.assignmentlab2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var users = arrayOf(
        User("abc", "123", "abc@miu.edu", "123"),
        User("aaa", "111", "aaa@miu.edu", "321"),
        User("zzz", "222", "zzz@miu.edu", "321"),
        User("ccc", "333", "ccc@miu.edu", "123"),
        User("xxx", "vvv", "xxx@miu.edu", "123")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.button.setOnClickListener {
            val username = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            val userResult = users.findLast {
                it.userName == username && it.password == password
            }
            if (userResult != null) {
                val intent = Intent(this@MainActivity, CategoryActivity::class.java)
                intent.putExtra("user", userResult as java.io.Serializable)
                startActivity(intent);
            } else {
                showMsg("Invalid credentials")
            }

        }

        var resultUser = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
            // result object contains the intent and RESULT_OK or RESULT_CANCEL
            if(result.resultCode == Activity.RESULT_OK) {
                // result.getIntent().getdata().toString()
                val newUser = result.data?.getSerializableExtra("user") as User
                users +=  newUser
                showMsg("User created successfully ${newUser.userName}")
            }
            else {
                showMsg("User did not created")
            }
        }

        binding.btnCreate.setOnClickListener {
            var intent = Intent(this,CreateUserActivity::class.java)
            intent.putExtra("users", users)
            resultUser.launch(intent)
        }

        binding.txtForgetPassword.setOnClickListener {

            val username = binding.edtEmail.text.toString()
            val userResult = users.findLast {
                it.userName == username
            }
            if (userResult != null) {
                var intent = Intent(Intent.ACTION_SENDTO)
                intent.data = Uri.parse("mailto:${userResult?.userName}?subject=ForgetPassword&body=${userResult?.password}")
                //Below code doesn't work
                //intent.putExtra(Intent.EXTRA_SUBJECT, "forget password ")
                //intent.putExtra(Intent.EXTRA_TEXT, "this is your password ${userResult?.password}")

                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent);
                } else {
                    showMsg("Don't have Mail App")
                }
            } else {
                showMsg("User not found")
            }
        }

    }

    private fun showMsg(msg: String) {
        Toast.makeText(this, msg,Toast.LENGTH_SHORT).show()
    }

}