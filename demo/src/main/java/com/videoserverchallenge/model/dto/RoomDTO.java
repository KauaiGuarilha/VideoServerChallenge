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
public class RoomDTO {

    private UUID id;
    private String roomName;
    private List<UserDTO> users;
    private UserDTO userHost;
    private Integer capacityLimit = 5;
}
