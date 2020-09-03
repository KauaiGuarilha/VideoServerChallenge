package com.videoserverchallenge.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTOResponse {

    private UUID id;
    private String roomName;
    private String userHost;
    private Integer capacityLimit = 5;
}
