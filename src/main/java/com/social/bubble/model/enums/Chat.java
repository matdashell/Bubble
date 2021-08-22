package com.social.bubble.model.enums;

public enum Chat {

    GLOBAL("global"),
    PUBLIC("publico"),
    PRIVATE("privado"),
    AUTH("autenticado");

    private String description;

    public String description() {
        return description;
    }

    Chat(String tipo) { }
}
