package com.example.autenticaofirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    val COD_TELA = 2
    private val autentication by lazy {
        FirebaseAuth.getInstance()
    }
    data class UserModelo(val email: String, val senha: String)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var login = findViewById<Button>(R.id.btnLogin)
        var cadastro = findViewById<Button>(R.id.btnCadastro)
        val user = autentication.currentUser
        if (user != null) {
            abrirInicio()
        }

        login.setOnClickListener {
            var email = findViewById<EditText>(R.id.etEmail)
            var senha = findViewById<EditText>(R.id.etSenha)
            var txtEmail = email?.text.toString()
            var txtSenha = senha?.text.toString()


            if (txtEmail.isNullOrEmpty() && txtSenha.isNullOrEmpty()) {
                Toast.makeText(this, "Preencher todos os campos", Toast.LENGTH_SHORT).show()

            } else {
                login()
            }

        }
        cadastro.setOnClickListener {
            var email = findViewById<EditText>(R.id.etEmail)
            var senha = findViewById<EditText>(R.id.etSenha)
            var txtEmail = email?.text.toString()
            var txtSenha = senha?.text.toString()

            if (txtEmail.isNullOrEmpty() && txtSenha.isNullOrEmpty()) {
                Toast.makeText(this, "Preencher todos os campos", Toast.LENGTH_SHORT).show()
            } else {
                cadastro()
            }
        }
    }

    fun cadastro() {
        var email = findViewById<EditText>(R.id.etEmail)
        var senha = findViewById<EditText>(R.id.etSenha)
        var txtEmail = email?.text.toString()
        var txtSenha = senha?.text.toString()
        val usuario = UserModelo(txtEmail,txtSenha)
        autentication.createUserWithEmailAndPassword(
            usuario.email, usuario.senha
        ).addOnSuccessListener {
            Toast.makeText(this, "cadastrado com sucesso!", Toast.LENGTH_LONG).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Erro ao efetuar o cadastro", Toast.LENGTH_LONG).show()
        }
    }
    fun login() {
        var email = findViewById<EditText>(R.id.etEmail)
        var senha = findViewById<EditText>(R.id.etSenha)
        var txtEmail = email?.text.toString()
        var txtSenha = senha?.text.toString()
        val usuario = UserModelo(txtEmail,txtSenha)
        autentication.signInWithEmailAndPassword(
            usuario.email, usuario.senha).addOnSuccessListener {
            abrirInicio()
        }.addOnFailureListener{
            Toast.makeText(this, "erro ao efetuar o login", Toast.LENGTH_LONG).show()
        }
    }

    fun abrirInicio() {
        var email = findViewById<EditText>(R.id.etEmail)
        var senha = findViewById<EditText>(R.id.etSenha)
        var txtEmail = email?.text.toString()
        var txtSenha = senha?.text.toString()
        val usuario = UserModelo(txtEmail,txtSenha)
        val intent = Intent(this, Tela2::class.java).apply {
            putExtra("senha", usuario.senha)
            putExtra("email", usuario.email)

        }

        startActivityForResult(intent,COD_TELA)
        senha.setText("")
        email.setText("")
    }


}