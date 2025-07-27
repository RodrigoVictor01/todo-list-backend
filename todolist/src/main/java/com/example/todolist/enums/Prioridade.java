package com.example.todolist.enums;

public enum Prioridade {
    BAIXA("Baixa"),
    MEDIA("Média"),
    ALTA("Alta");

    private final String descricao;

    Prioridade(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao() {
        return descricao;
    }
}
