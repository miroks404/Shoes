package com.example.shoes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.shoes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val emailText = binding.etEmail.text
        val passwordText = binding.etPassword.text

        binding.bEnter.setOnClickListener {
            if (emailText.isEmpty() || passwordText.isEmpty()) {
                showErrorDialog(
                    this@MainActivity,
                    "Ошибка!",
                    "Одно из полей не заполнено"
                )
                return@setOnClickListener
            }
            if (isValidEmail(emailText)) {
                val intent = Intent(this@MainActivity, MainActivity2::class.java)
                startActivity(intent)
            } else {
                showErrorDialog(this@MainActivity, "Неверный email", "Введен невалидный email")
            }
        }
    }
}

private fun isValidEmail(email: Editable): Boolean =
    email.matches(Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"))

private fun showErrorDialog(context: Context, title: String, message: String) {
    val builder = AlertDialog.Builder(context)
    builder.setTitle(title)
        .setMessage(message)
        .setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        .setCancelable(false)
    val dialog = builder.create()
    dialog.show()
}