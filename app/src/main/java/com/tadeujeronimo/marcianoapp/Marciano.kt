package com.tadeujeronimo.marcianoapp

import kotlin.math.*

fun interface AcaoPersonalizada {
    fun executar(): String
}

open class Marciano {
    val pergunta = "Certamente"
    val grito = "Opa! Calma aí!"
    val pergunta_grito = "Relaxa, eu sei o que estou fazendo!"
    val eu = "A responsabilidade é sua"
    val vazio = "Não me incomode"
    val outro = "Tudo bem, como quiser"

    open fun responde(frase: String): String {
        val trimmed = frase.trim()
        return when {
            trimmed.isEmpty() -> vazio
            trimmed.contains("eu", ignoreCase = true) -> eu
            isShouting(trimmed) && trimmed.endsWith("?") -> pergunta_grito
            isShouting(trimmed) -> grito
            trimmed.endsWith("?") -> pergunta
            else -> outro
        }
    }

    private fun isShouting(s: String): Boolean {
        val letters = s.filter { it.isLetter() }
        return letters.isNotEmpty() && letters.all { it.isUpperCase() }
    }

    // Compatibilidade com o código original do app
    open fun responda(frase: String): String = responde(frase)
}

open class MarcianoMatematico : Marciano() {
    open fun responde(operacao: String, vararg operandos: Double): String {
        if (operandos.isEmpty()) return "Ou não"
        
        val op = operacao.lowercase()
        val res = when (op) {
            "some" -> if (operandos.size >= 2) operandos[0] + operandos[1] else return "Preciso de 2 números"
            "subtraia" -> if (operandos.size >= 2) operandos[0] - operandos[1] else return "Preciso de 2 números"
            "multiplique" -> if (operandos.size >= 2) operandos[0] * operandos[1] else return "Preciso de 2 números"
            "divida" -> {
                if (operandos.size < 2) return "Preciso de 2 números"
                if (operandos[1] == 0.0) "Não posso dividir por zero." else operandos[0] / operandos[1]
            }
            else -> return super.responde(operacao)
        }
        
        return if (res is String) res else "Essa eu sei, ${formataNumero(res as Double)}"
    }

    open fun responda(operacao: String, vararg operandos: Double): String = responde(operacao, *operandos)

    protected fun formataNumero(n: Double): String {
        return if (n % 1.0 == 0.0) n.toLong().toString() else n.toString()
    }
}

class MarcianoPremium(
    private val acaoPersonalizada: AcaoPersonalizada
) : MarcianoMatematico() {
    override fun responde(frase: String): String {
        if (frase.trim().equals("agir", ignoreCase = true)) {
            return acaoPersonalizada.executar()
        }

        return super.responde(frase)
    }

    override fun responde(operacao: String, vararg operandos: Double): String {
        val op = operacao.lowercase()

        when (op) {
            "raiz" -> {
                if (operandos.size != 1) return "Preciso de exatamente um número para raiz."
                val x = operandos.first()
                if (x < 0.0) return "Não posso calcular a raiz de número negativo."
                val resultado = sqrt(x)
                return "Essa eu sei, ${formataNumero(resultado)}"
            }

            "potencia" -> {
                if (operandos.size != 2) return "Preciso de dois números (base e expoente)."
                val base = operandos[0]
                val expo = operandos[1]
                val resultado = base.pow(expo)
                return "Essa eu sei, ${formataNumero(resultado)}"
            }

            "modulo" -> {
                if (operandos.size != 2) return "Preciso de dois números para módulo."
                val a = operandos[0]
                val b = operandos[1]
                if (b == 0.0) return "Não posso dividir por zero."
                val resultado = a % b
                return "Essa eu sei, ${formataNumero(resultado)}"
            }

            "percentual" -> {
                if (operandos.size != 2) return "Preciso de dois números (valor e percentual)."
                val valor = operandos[0]
                val perc = operandos[1]
                val resultado = valor * perc / 100.0
                return "Essa eu sei, ${formataNumero(resultado)}"
            }
        }

        // Para operações básicas delega para MarcianoMatematico
        return super.responde(op, *operandos)
    }

    override fun responda(operacao: String, vararg operandos: Double): String {
        return responde(operacao, *operandos)
    }
}
