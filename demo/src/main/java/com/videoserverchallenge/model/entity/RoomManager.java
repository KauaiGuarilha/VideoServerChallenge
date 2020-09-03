package com.videoserverchallenge.model.entity;

import com.videoserverchallenge.model.dto.RoomDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class RoomManager {

    private static List<RoomDTO> roomList = new ArrayList<>();

    public static List<RoomDTO> getRoomList() {
        return roomList;
    }

    public static void setRoomList(RoomDTO room) {
        room.setId(UUID.randomUUID());
        RoomManager.roomList.add(room);
    }

    public static void removeRoom(RoomDTO room) {
        RoomManager.roomList.remove(room);
    }
}
