package com.tadeujeronimo.marcianoapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val root = findViewById<View>(R.id.main_root)
        ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editMensagem = findViewById<EditText>(R.id.editMensagem)
        val btnEnviar = findViewById<Button>(R.id.btnEnviar)
        val btnMatematica = findViewById<Button>(R.id.btnMatematica)
        val btnHistorico = findViewById<Button>(R.id.btnHistorico)
        val btnInfo = findViewById<ImageButton>(R.id.btnInfo)

        btnEnviar.setOnClickListener {
            val mensagem = editMensagem.text.toString().trim()
            val intent = Intent(this, RespostaActivity::class.java)
            intent.putExtra("MENSAGEM", mensagem)
            startActivity(intent)
        }

        btnMatematica.setOnClickListener {
            val intent = Intent(this, MatematicaActivity::class.java)
            startActivity(intent)
        }

        btnHistorico.setOnClickListener {
            val intent = Intent(this, HistoricoActivity::class.java)
            startActivity(intent)
        }

        btnInfo.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("👨🏽‍💻 Desenvolvedor")
                .setMessage("Nome: Tadeu dos Santos Jerônimo\nMatrícula: 2026202194\nE-mail: tadeus.jeronimo@gmail.com")
                .setPositiveButton("Fechar", null)
                .show()
        }
    }

    override fun onResume() {
        super.onResume()
        findViewById<EditText>(R.id.editMensagem).setText("")
    }
}
