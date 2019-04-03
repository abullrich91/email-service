package com.agencia.trueno.email.repository;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.agencia.trueno.email.config.FirebaseConfig;
import com.agencia.trueno.email.model.EmailFormRequest;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FormRepository {

    private final FirebaseConfig firebaseConfig;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void storeFormInfo(EmailFormRequest request) throws Exception {
        try {
            DocumentReference docRef = firebaseConfig.firestore()
                .collection("cloud-users")
                .document(dateFormat.format(new Date()));

            // Add document data  with id "alovelace" using a hashmap
            Map<String, Object> data = new HashMap<>();
            data.put("fullName", request.getFullName());
            data.put("organization", request.getOrganization());
            data.put("phone", request.getPhone());
            data.put("email", request.getEmail());
            data.put("message", request.getMessage());

            //asynchronously write data
            ApiFuture<WriteResult> result = docRef.set(data);
            result.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new ExecutionException("Execution error while storing user info", e);
        } catch (Exception e) {
            throw new Exception("Unexpected error while storing user info", e);
        }
    }
}
