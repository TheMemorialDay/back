package com.korit.thememorialday.controller.firestore;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korit.thememorialday.service.implement.firestore.KeywordService;

//* React가 호출할 수 있는 API */

@RestController
@RequestMapping("/keywords")
public class KeywordController {

	private final KeywordService keywordService;

    public KeywordController(KeywordService keywordService) {
        this.keywordService = keywordService;
    }

    @PostMapping
    public void addKeyword(@RequestBody String keyword) {
        keywordService.saveKeyword(keyword);
    }

    @GetMapping
    public List<String> getKeywords() throws ExecutionException, InterruptedException {
        return keywordService.getKeywords();
    }
	
}
