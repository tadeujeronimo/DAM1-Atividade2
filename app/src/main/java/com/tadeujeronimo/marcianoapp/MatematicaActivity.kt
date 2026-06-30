package com.tadeujeronimo.marcianoapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MatematicaActivity : AppCompatActivity() {

    // Operações que exigem dois números (conforme MarcianoPremium)
    private val operacoesDois = listOf("some", "subtraia", "multiplique", "divida", "potencia", "modulo", "percentual")
    // Apenas raiz precisa de um número
    private val operacoesUm = listOf("raiz")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matematica)

        val spinnerOp = findViewById<Spinner>(R.id.spinnerOperacao)
        val editNum1 = findViewById<EditText>(R.id.editNum1)
        val editNum2 = findViewById<EditText>(R.id.editNum2)
        val labelNum2 = findViewById<TextView>(R.id.labelNum2)
        val dividerNum2 = findViewById<View>(R.id.dividerNum2)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val btnVoltar = findViewById<Button>(R.id.btnVoltar)

        val todasOperacoes = operacoesDois + operacoesUm
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, todasOperacoes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerOp.adapter = adapter

        spinnerOp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                val op = todasOperacoes[pos]
                val precisaDois = operacoesDois.contains(op)
                val visibility = if (precisaDois) View.VISIBLE else View.GONE
                
                editNum2.visibility = visibility
                labelNum2.visibility = visibility
                dividerNum2.visibility = visibility
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        btnCalcular.setOnClickListener {
            val operacao = spinnerOp.selectedItem.toString()
            val num1Str = editNum1.text.toString()

            if (num1Str.isBlank()) {
                editNum1.error = "Informe um número"
                return@setOnClickListener
            }

            val num1 = num1Str.toDoubleOrNull()
            if (num1 == null) {
                editNum1.error = "Número inválido"
                return@setOnClickListener
            }

            val marciano = MarcianoPremium { "Ação personalizada executada!" }
            val resposta: String

            if (operacoesDois.contains(operacao)) {
                val num2Str = editNum2.text.toString()
                if (num2Str.isBlank()) {
                    editNum2.error = "Informe o segundo número"
                    return@setOnClickListener
                }
                val num2 = num2Str.toDoubleOrNull()
                if (num2 == null) {
                    editNum2.error = "Número inválido"
                    return@setOnClickListener
                }
                // Usa a nova assinatura responde(operacao, vararg operandos)
                resposta = marciano.responde(operacao, num1, num2)
            } else {
                // Apenas raiz cai aqui
                resposta = marciano.responde(operacao, num1)
            }

            val comandoTexto = if (operacoesDois.contains(operacao))
                "$operacao $num1 ${editNum2.text}"
            else
                "$operacao $num1"

            HistoricoManager.adicionar(comandoTexto, resposta)

            val intent = Intent(this, RespostaMatematicaActivity::class.java)
            intent.putExtra("OPERACAO", operacao)
            intent.putExtra("RESPOSTA", resposta)
            startActivity(intent)
        }

        btnVoltar.setOnClickListener { finish() }
    }
}
