package com.videoserverchallenge.utils;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class InitializationUtils {

    @Value("${firebase.database.url}")
    private String databaseUrl;

    @Value("${firebase.storage.bucket}")
    private String storageBucket;

    @Value("${firebase.storage.googleapis}")
    private String storageGoogleApis;
}
