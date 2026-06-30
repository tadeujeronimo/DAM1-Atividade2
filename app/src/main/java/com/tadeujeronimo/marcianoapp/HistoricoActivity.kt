package com.tadeujeronimo.marcianoapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HistoricoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historico)

        // Ajuste para evitar sobreposição pela barra de navegação do sistema
        val root = findViewById<View>(R.id.historico_root)
        ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val listView = findViewById<ListView>(R.id.listHistorico)
        val txtVazio = findViewById<TextView>(R.id.txtVazio)
        val btnVoltar = findViewById<Button>(R.id.btnVoltar)

        val historico = HistoricoManager.getHistorico()

        if (historico.isEmpty()) {
            listView.visibility = View.GONE
            txtVazio.visibility = View.VISIBLE
        } else {
            listView.visibility = View.VISIBLE
            txtVazio.visibility = View.GONE

            val itens = historico.map { "\"${it.mensagem}\"\n→ ${it.resposta}" }
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, itens)
            listView.adapter = adapter

            listView.setOnItemClickListener { _, _, position, _ ->
                val entrada = historico[position]
                val intent = Intent(this, RespostaActivity::class.java)
                intent.putExtra("MENSAGEM", entrada.mensagem)
                startActivity(intent)
            }
        }

        btnVoltar.setOnClickListener { finish() }
    }
}
