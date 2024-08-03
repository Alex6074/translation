package com.example.translation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TranslationRecord {
    private String ip;
    private String inputText;
    private String translatedText;
}
