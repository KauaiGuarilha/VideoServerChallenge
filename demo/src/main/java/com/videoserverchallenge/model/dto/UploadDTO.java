package com.videoserverchallenge.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadDTO {

    private String fileName;
    private String mimeType;
    private String base64;
}
