package com.example.todolist.enums;

public enum Status {
    EM_ANDAMENTO("Em Andamento"),
    CONCLUIDA("Concluída"),
    PENDENTE("Pendente");

    private final String descricao;

    Status(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
