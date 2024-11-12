package com.korit.thememorialday.service.implement.firestore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

//* Firestore에 키워드를 저장하고 조회하는 서비스 */

@Service
public class KeywordService {

	private final Firestore firestore;

    public KeywordService() {
        this.firestore = FirestoreOptions.getDefaultInstance().getService();
    }

    public void saveKeyword(String keyword) {
        firestore.collection("keywords").add(new Keyword(keyword));
    }

    public List<String> getKeywords() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> query = firestore.collection("keywords").get();
        List<QueryDocumentSnapshot> documents = query.get().getDocuments();
        List<String> keywords = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            keywords.add(document.getString("keyword"));
        }
        return keywords;
    }
	
}

class Keyword {
    private String keyword;

    public Keyword(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}


