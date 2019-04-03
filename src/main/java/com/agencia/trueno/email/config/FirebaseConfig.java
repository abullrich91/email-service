package com.agencia.trueno.email.config;

import java.io.FileInputStream;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FirebaseConfig {

    @Bean
    public Firestore firestore() throws IOException {
        return enableFirebase();
    }

    private Firestore enableFirebase() throws IOException {
        FirestoreOptions firestoreOptions;
        try {
            FileInputStream serviceAccount =
                new FileInputStream("serviceAccountKey.json");

            firestoreOptions = FirestoreOptions.newBuilder()
                .setTimestampsInSnapshotsEnabled(true)
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

            firestoreOptions.getService();
        } catch (IOException e) {
            throw new IOException("Unable to connect to Firebase", e);
        }
        return firestoreOptions.getService();
    }

}
