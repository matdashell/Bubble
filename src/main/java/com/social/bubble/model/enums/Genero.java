package com.social.bubble.model.enums;

public enum Genero {

    MASCULINO("Masculino"),
    FEMININO("Feminino"),
    NEUTRO("Neutro");

    private String description;

    public String description() {
        return description;
    }

    Genero(String genero) { }
}
