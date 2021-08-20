package com.social.bubble.model.enums;

public enum Cores {

    BRANCO("Bracno"),
    PRETO("Preto"),
    AMARELO("Amarelo"),
    AZUL("Azul"),
    VERDE("Verde"),
    ROXO("Roxo"),
    VERMELHO("Vermelho");

    Cores(String cor) { }

    private String description;

    public String description() {
        return description;
    }
}
