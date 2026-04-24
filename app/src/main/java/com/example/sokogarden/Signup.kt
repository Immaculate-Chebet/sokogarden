package com.example.sokogarden

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.loopj.android.http.RequestParams

class Signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)

        // find all view by use of their ids

        val username = findViewById< EditText>(R.id.username)
        val email = findViewById<EditText>(R.id.email)
        val password=findViewById<EditText>(R.id.password)
        val phone=findViewById<EditText>(R.id.phone)
        val signupButton=findViewById<Button>(R.id.signupBtn)
        val signinTextView=findViewById<TextView>(R.id.signintxt)


        signinTextView.setOnClickListener {
            val intent= Intent(applicationContext, Signin::class.java)
            startActivity(intent)
        }
        signupButton.setOnClickListener {
            val api="https://clate.alwaysdata.net/api/signup"

            val data= RequestParams()

            data.put("username",username.text.toString().trim())
            data.put("email",email.text.toString().trim())
            data.put("password",password.text.toString().trim())
            data.put("phone",phone.text.toString().trim())

            val helper= ApiHelper(applicationContext)

            helper.post(api,data)

            email.text.clear()
            password.text.clear()
            phone.text.clear()
            username.text.clear()

            val intent= Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }




    }
    }
