package com.pritimahajan.crudfirestore.service;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import com.pritimahajan.crudfirestore.domain.HistoryData;
import com.pritimahajan.crudfirestore.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class UserHistoryService
{


    @Value("${spring.cloud.gcp.firestore.project-id}")
    private String projectId;

    public boolean saveUser(User user) throws ExecutionException, InterruptedException, IOException
    {

        DocumentReference frankDocRef = getConn().collection("users").document(user.getEmail());

        Map<String, Object> initialData = new HashMap<>();
        initialData.put("name", user.getName());
        initialData.put("email", user.getEmail());
        initialData.put("createdAt", FieldValue.serverTimestamp());
        initialData.put("lastLoginAt", FieldValue.serverTimestamp());

        try
        {
            ApiFuture<WriteResult> initialResult = frankDocRef.create(initialData);

            System.out.println("Update time : " + initialResult.get().getUpdateTime());

        }catch( Exception ex){

            if (ex.getCause().getClass().toString().contains("com.google.api.gax.rpc.AlreadyExistsException")){
                ApiFuture<WriteResult> initialResult = frankDocRef.set(initialData, SetOptions.mergeFields("name", "lastLoginAt"));

                System.out.println("Update time : " + initialResult.get().getUpdateTime());
            }else{
            System.out.println(" Error =======> "+ ex.getMessage());}
        }
        return true;
    }


    public boolean saveHistory(HistoryData historyData) throws ExecutionException, InterruptedException, IOException
    {
        DocumentReference docRef = getConn().collection("historyData").document(historyData.getTimestamp());
        Map<String, Object> data = new HashMap<>();
        data.put("email", historyData.getEmail());
        data.put("url", historyData.getUrl());
        data.put("timestamp", historyData.getTimestamp());
        data.put("createdTimestamp", FieldValue.serverTimestamp());

        ApiFuture<WriteResult> initialResult = docRef.set(data);

        System.out.println("inserted time : " + initialResult.get());

        return true;
    }

    private Firestore getConn() throws IOException
    {
        FirestoreOptions firestoreOptions =
                FirestoreOptions.getDefaultInstance().toBuilder()
                        .setProjectId(projectId)
                        .setCredentials(GoogleCredentials.getApplicationDefault())
                        .build();
        return firestoreOptions.getService();

    }
}


