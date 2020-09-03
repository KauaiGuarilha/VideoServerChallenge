package com.videoserverchallenge.model.service;

import com.videoserverchallenge.model.dto.ChangeUserDTO;
import com.videoserverchallenge.model.dto.RoomDTO;
import com.videoserverchallenge.model.dto.UserDTO;
import com.videoserverchallenge.model.entity.RoomManager;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    @Autowired RoomManager roomManager;

    public RoomDTO createRoom(RoomDTO dto) {
        roomManager.setRoomList(dto);
        return dto;
    }

    public RoomDTO returnInfoRoom(UUID idRoom) {
        for (RoomDTO room : roomManager.getRoomList()) {
            if (room.getId().equals(idRoom)) {
                return room;
            }
        }
        throw new RuntimeException("Could not get room information.");
    }

    public void enterRoom(UserDTO dto, UUID idRoom) {

        for (RoomDTO room : roomManager.getRoomList()) {
            if (room.getId().equals(idRoom)) {
                if (room.getUsers().size() <= room.getCapacityLimit()) room.getUsers().add(dto);
                else throw new RuntimeException("Could not enter the room.");
            }
        }
    }

    public void alterRoomHost(ChangeUserDTO dto, UUID idRoom) {

        for (RoomDTO room : roomManager.getRoomList()) {
            if (room.getId().equals(idRoom)) {
                if (room.getUserHost().equals(dto.getUserHost()))
                    room.setUserHost(dto.getUserChange());
                else throw new RuntimeException("Could not change room host.");
            }
        }
    }

    public void leaveRoom(UserDTO dto, UUID idRoom) {

        for (RoomDTO room : roomManager.getRoomList()) {
            if (room.getId().equals(idRoom)) {
                if (room.getUsers().contains(dto)) room.getUsers().remove(dto);
                else throw new RuntimeException("Could not leave the room.");
            }
        }
    }

    public List<RoomDTO> returnRoomsByUser(String username) {
        return findRooms(username);
    }

    private List<RoomDTO> findRooms(String username) {
        List<RoomDTO> rooms = new ArrayList<>();
        for (RoomDTO room : roomManager.getRoomList()) {
            boolean userFinded = false;
            for (UserDTO user : room.getUsers()) {
                if (user.getName().equals(username)) {
                    rooms.add(room);
                    userFinded = true;
                    break;
                }
            }
            if (userFinded) {
                continue;
            }
        }
        return rooms;
    }
}