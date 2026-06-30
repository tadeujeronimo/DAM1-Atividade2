package com.tadeujeronimo.marcianoapp

data class EntradaHistorico(val mensagem: String, val resposta: String)

object HistoricoManager {
    private val historico = mutableListOf<EntradaHistorico>()

    fun adicionar(mensagem: String, resposta: String) {
        historico.add(0, EntradaHistorico(mensagem, resposta))
    }

    fun getHistorico(): List<EntradaHistorico> = historico.toList()

    fun limpar() = historico.clear()
}
