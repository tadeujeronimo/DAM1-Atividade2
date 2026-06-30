package com.tadeujeronimo.marcianoapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RespostaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resposta)

        val mensagem = intent.getStringExtra("MENSAGEM") ?: ""

        val marciano = MarcianoPremium { "Ação personalizada executada!" }
        val resposta = marciano.responda(mensagem)

        // Salva no histórico
        HistoricoManager.adicionar(mensagem, resposta)

        val txtMensagemEnviada = findViewById<TextView>(R.id.txtMensagemEnviada)
        val txtResposta = findViewById<TextView>(R.id.txtResposta)
        val btnVoltar = findViewById<Button>(R.id.btnVoltar)

        txtMensagemEnviada.text = "Você disse: \"$mensagem\""
        txtResposta.text = resposta

        btnVoltar.setOnClickListener {
            finish()
        }
    }
}
