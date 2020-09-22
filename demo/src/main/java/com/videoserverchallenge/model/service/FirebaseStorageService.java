package com.videoserverchallenge.model.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import com.videoserverchallenge.model.entity.Room;
import com.videoserverchallenge.model.repository.RoomRepository;
import com.videoserverchallenge.utils.InitializationUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FirebaseStorageService {

    @Autowired private RoomRepository repository;
    @Autowired private InitializationUtils initializationUtils;

    public String upload(String fName, String mimiType, MultipartFile file) throws IOException {
        Bucket bucket = StorageClient.getInstance().bucket();
        System.out.println(bucket);

        byte[] bytes = file.getBytes();

        Blob blob = bucket.create(fName, bytes, mimiType);

        // URL public
        blob.createAcl(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));

        return String.format(initializationUtils.getStorageGoogleApis(), bucket.getName(), fName);
    }

    public String uploadDatabase(String fName, String mimiType, MultipartFile file)
            throws IOException {

        Bucket bucket = StorageClient.getInstance().bucket();

        Room room =
                Room.builder()
                        .pathImg(
                                String.format(
                                        initializationUtils.getStorageGoogleApis(),
                                        bucket.getName(),
                                        fName))
                        .build();

        repository.save(room);

        return Base64.getEncoder().encodeToString(file.getBytes());
    }

    @PostConstruct
    private void init() throws IOException {
        if (FirebaseApp.getApps().isEmpty()) {
            InputStream inputStream =
                    FirebaseStorageService.class.getResourceAsStream("/serviceAccountKey.json");

            System.out.println(inputStream);

            FirebaseOptions options =
                    new FirebaseOptions.Builder()
                            .setCredentials(GoogleCredentials.fromStream(inputStream))
                            .setStorageBucket(initializationUtils.getStorageBucket())
                            .setDatabaseUrl(initializationUtils.getDatabaseUrl())
                            .build();

            FirebaseApp.initializeApp(options);
        }
    }
}
