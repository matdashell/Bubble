package com.social.bubble.model.enums;

public enum Animais {

    GATO("Gato"),
    CACHORRO("Cachorro"),
    LEAO("Leão"),
    COBRA("Cobra"),
    CAPIVARA("Capivara"),
    PAPAGAIO("Papagaio"),
    RAMMISTER("Rammister"),
    CAVALO("Cavalo");

    Animais(String animal) { }

    private String description;

    public String description(){
        return description;
    }
}
