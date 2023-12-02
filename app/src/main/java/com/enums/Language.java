package com.enums;

public enum Language {
    ENGLISH("en"),
    GERMAN("de");

    private final String languageCode;

    public String getLanguageCode() {
        return this.languageCode;
    }

    Language(String languageCode) {
        this.languageCode = languageCode;
    }
}