package com.example.translation.service;

import com.example.translation.database.TranslationRecordRepository;
import com.example.translation.model.TranslationRecord;
import com.example.translation.model.TranslationRequest;
import com.example.translation.model.TranslationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class TranslationService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TranslationRecordRepository repository;

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public TranslationResponse translate(TranslationRequest request, String ip) {
        String[] words = request.getText().split("\\s+");
        Future<String>[] futures = new Future[words.length];

        for (int i = 0; i < words.length; i++) {
            final String word = words[i];
            futures[i] = executorService.submit(() -> translateWord(word, request.getSourceLang(), request.getTargetLang()));
        }

        StringBuilder translatedText = new StringBuilder();
        for (Future<String> future : futures) {
            try {
                translatedText.append(future.get()).append(" ");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String translated = translatedText.toString().trim();

        TranslationRecord record = new TranslationRecord(ip, request.getText(), translated);
        repository.save(record);

        return new TranslationResponse(translated);
    }

    private String translateWord(String word, String sourceLang, String targetLang) {
        // TODO
        // replace with actual API call
        String url = String.format("https://translation.api/service?key=%s&text=%s&source=%s&target=%s",
                "api_key", word, sourceLang, targetLang);
        return restTemplate.getForObject(url, String.class);
    }
}
