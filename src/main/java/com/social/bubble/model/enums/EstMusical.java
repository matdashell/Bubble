package com.social.bubble.model.enums;

public enum EstMusical {

    ROCK("Rock"),
    POP("Pop"),
    SERTANEJO("Sertanejo"),
    PAGODE("Pagode"),
    RAGGAE("Raggae"),
    BLUES("Blues"),
    TRAP("Trap"),
    RAP("Rap"),
    INDIE("Indie");

    EstMusical(String estilo) { }

    private String description;

    public String description(){
        return description;
    }
}
