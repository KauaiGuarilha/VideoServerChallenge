package com.videoserverchallenge.model.factory;

import com.videoserverchallenge.model.dto.RoomDTO;
import com.videoserverchallenge.model.dto.RoomDTOResponse;
import org.springframework.stereotype.Component;

@Component
public class RoomDTOResponseFactory {

    public RoomDTOResponse dtoToResponse(RoomDTO dto){
        return RoomDTOResponse.builder()
                .id(dto.getId())
                .roomName(dto.getRoomName())
                .userHost(dto.getUserHost().getName())
                .capacityLimit(dto.getCapacityLimit())
                .build();
    }
}
