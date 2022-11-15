package org.example;


import lombok.Getter;

@Getter
public enum Variables {

    CLIENT_CHOICE ("clientChoice"),
    CREDIT_VARIATIONS ("creditVariations"),

    APPROVE_CREDIT ("approveCredit"),
    CLIENT ("client");

    final String variable;

    Variables(String variable) {
        this.variable = variable;
    }



}
