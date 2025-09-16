package com.example.lifecycle

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.Scanner

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val createMembershipButton = findViewById<Button>(R.id.newMemberBtn)
        val loginBtn = findViewById<Button>(R.id.loginBtn)
        val usernameInput = findViewById<EditText>(R.id.usernameInput)
        val passwordInput = findViewById<EditText>(R.id.passwordInput)

//        enableEdgeToEdge()
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            // okej. Jag ska då ha två sidor.
            // Så om denna är inlogg sidan, så är nästa en "Välkommen in" Sida
            // dock tror jag att det tekniskt sett blir tre...
            // Spec om jag ska ha en registrera sida... Men vi kanske kan göra det enkelt


        // login
        // loginBtn onClick
        // koppla in membership login och password

            // två inputs
            // anv namn
            // lösen
            // knapp



        loginBtn.setOnClickListener {
            val sharedPref = getSharedPreferences("UserPrefs", MODE_PRIVATE)
            val savedUser = sharedPref.getString("username", null)
            val savedPass = sharedPref.getString("password", null)

            // trim hjälper till att ta bort ev mellanslag
            // som anv ev råkar lägga till
            val memberUser = usernameInput.text.toString().trim()
            val memberPass = passwordInput.text.toString().trim()

            if (memberUser == savedUser && memberPass == savedPass) {
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)

            } else {
                Toast.makeText(this, "Incorrect username and/or password", Toast.LENGTH_SHORT).show()
            }
        }

            // en "länk" till registrera
        // ändrat från text till button
        createMembershipButton.setOnClickListener {
            val intent = Intent(this, MembershipActivity::class.java)
            startActivity(intent)
        }





//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets

    }
}

