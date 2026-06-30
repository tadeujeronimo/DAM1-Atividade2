package com.tadeujeronimo.marcianoapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RespostaMatematicaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resposta)

        val operacao = intent.getStringExtra("OPERACAO") ?: ""
        val resposta = intent.getStringExtra("RESPOSTA") ?: ""

        findViewById<TextView>(R.id.txtMensagemEnviada).text = "Operação: $operacao"
        findViewById<TextView>(R.id.txtResposta).text = resposta

        findViewById<Button>(R.id.btnVoltar).setOnClickListener { finish() }
    }
}
