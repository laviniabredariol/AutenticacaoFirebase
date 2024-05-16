package com.example.autenticaofirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class Tela2 : AppCompatActivity() {
    private val autentication by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela2)
        val email = findViewById<TextView>(R.id.idmail)
        // val senha = findViewById<TextView>(R.id.idsenha)
        email.text = autentication.currentUser?.email
        val btnsair = findViewById<Button>(R.id.btnTela2)
        btnsair.setOnClickListener {
            logout()
        }
    }

    fun logout() {
        autentication.signOut()
        finish()
    }
}

