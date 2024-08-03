package com.example.translation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TranslationRequest {
    private String text;
    private String sourceLang;
    private String targetLang;
}
