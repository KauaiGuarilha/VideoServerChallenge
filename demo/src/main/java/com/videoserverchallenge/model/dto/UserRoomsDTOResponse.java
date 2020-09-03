package com.videoserverchallenge.model.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRoomsDTOResponse {

    private String username;
    private List<RoomDTOResponse> rooms;
}
