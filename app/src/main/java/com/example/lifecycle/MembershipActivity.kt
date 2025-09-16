package com.example.lifecycle

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MembershipActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_membership)

        val usernameInput = findViewById<EditText>(R.id.username)
        val passwordInput = findViewById<EditText>(R.id.password)
        val checkBox = findViewById<CheckBox>(R.id.checkBox)
        val radioFemale = findViewById<RadioButton>(R.id.radioFemale)
        val radioMale = findViewById<RadioButton>(R.id.radioMale)
        val switch18 = findViewById<Switch>(R.id.switch1)
        val registerBtn = findViewById<Button>(R.id.button)



        registerBtn.setOnClickListener {
            val username = usernameInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()
            val isNotRobot = checkBox.isChecked
            val isFemale = radioFemale.isChecked
            val isMale = radioMale.isChecked
            val isOver18 = switch18.isChecked


            if (username.isEmpty()) {
                Toast.makeText(
                    this, "Please fill in your username",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                Toast.makeText(
                    this, "You need to fill in a password",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (!isNotRobot) {
                Toast.makeText(
                    this, "Please confirm you are not a robot!",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (!isOver18) {
                Toast.makeText(
                    this, "You must be over 18!",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val gender = when {
                isFemale -> "Female"
                isMale -> "Male"
                else -> "Not specified"
            }

            val errorMsg = validatePassword(password)

            if (errorMsg != null) {
                Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()

                return@setOnClickListener
            } else {
                val welMessage = "Welcome $username! You are now a LifeCycle member!"
                Toast.makeText(this, welMessage, Toast.LENGTH_SHORT).show()

            }

            // vi låtsas att vi har en databas
            // så kan vi förhoppningsvis logga in sen
            val sharedPref = getSharedPreferences("UserPrefs", MODE_PRIVATE)
            // val editor = sharedPref.edit()

            with(sharedPref.edit()) {
                putString("username", username)
                putString("password", password)

                apply()
            }

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

            finish()

        }

        val backToLoginButton = findViewById<Button>(R.id.backBtn)
        backToLoginButton.setOnClickListener {
            Toast.makeText(this, "Time To Login!", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }


    // detta är så att ditt lösenord blir starkt nog
    private fun validPass(password: String): Boolean {
        val passwordPattern = Regex("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$")
        return password.matches(passwordPattern)
    }

    // och dessa kraven uppfyller du eventuellt inte
    // när du gör ditt lösenord
    private fun validatePassword(password: String): String? {
        if (password.length < 8) return "Password needs at least 8 characters"
        if (!password.any { it.isUpperCase() }) return "You need at least one uppercase letter"
        if (!password.any { it.isLowerCase() }) return "You need at least one lowercase letter"
        if (!password.any { it.isDigit() }) return "Password must contain at least one number"

        return null
    }

//    // vi låtsas att vi har en databas
//    val sharedPref = getSharedPreferences("UserPrefs", MODE_PRIVATE)
//    val editor = sharedPref.edit()
//
//    editor.putString("username", username)
//    editor.putString("password", password)



}
