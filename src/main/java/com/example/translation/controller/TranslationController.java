package com.example.translation.controller;

import com.example.translation.model.TranslationRequest;
import com.example.translation.model.TranslationResponse;
import com.example.translation.service.TranslationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/translate")
public class TranslationController {
    @Autowired
    private TranslationService translationService;

    @PostMapping
    public TranslationResponse translate(@RequestBody TranslationRequest request, HttpServletRequest httpServletRequest) {
        String ip = httpServletRequest.getRemoteAddr();
        return translationService.translate(request, ip);
    }
}
