package com.korit.thememorialday.config;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

//* Firebase 설정을 초기화하는 클래스 */

@Configuration
public class FireBaseConfig {

	@Bean
	public void initializeFirebase() throws IOException {
        FileInputStream serviceAccount =
            new FileInputStream("src/main/resources/firebase-service-account.json");

        FirebaseOptions options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setDatabaseUrl("https://<your-project-id>.firebaseio.com")
            .build();

        FirebaseApp.initializeApp(options);
    }
	
}
