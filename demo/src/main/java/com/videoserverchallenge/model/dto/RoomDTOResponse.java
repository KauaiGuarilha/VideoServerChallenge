package com.videoserverchallenge.model.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
