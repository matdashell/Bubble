package com.social.bubble.model.enums;

public enum Chat {

    PUBLIC("Publico"),
    PRIVATE("Privado"),
    AUTH("Autenticado");

    private String description;

    public String description() {
        return description;
    }

    Chat(String tipo) { }
}
