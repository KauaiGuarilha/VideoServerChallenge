package com.videoserverchallenge.model.factory;

import com.videoserverchallenge.model.dto.*;
import com.videoserverchallenge.model.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRoomsDTOResponseFactory {

    public UserRoomsDTOResponse dtoToUserRooms(String user, List<RoomDTOResponse> rooms){
        return UserRoomsDTOResponse.builder()
                .username(user)
                .rooms(rooms)
                .build();
    }
}
