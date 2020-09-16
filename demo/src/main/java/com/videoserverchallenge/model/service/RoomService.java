package com.videoserverchallenge.model.service;

import com.videoserverchallenge.model.domain.EMessage;
import com.videoserverchallenge.model.dto.ChangeUserDTO;
import com.videoserverchallenge.model.dto.RoomDTO;
import com.videoserverchallenge.model.dto.UserDTO;
import com.videoserverchallenge.model.entity.RoomManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.videoserverchallenge.model.entity.User;
import com.videoserverchallenge.model.factory.UserDTOResponseFactory;
import com.videoserverchallenge.model.parse.UserParser;
import com.videoserverchallenge.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    @Autowired private UserParser roomParser;
    @Autowired private RoomManager roomManager;
    @Autowired private UserDTOResponseFactory factory;
    @Autowired private UserRepository repository;

    public RoomDTO createRoom(String nameRoom, Integer capacityRoom, String host, List<String> users) {
        UserDTO userHost = factory.toUserDTO(repository.findByUser(host));
        if (Objects.isNull(userHost)){
            throw new RuntimeException(EMessage.NO_USERS_REGISTERED.getMessage() + host);
        }

        List<User> listUsers = getUsers(users);

        List<UserDTO> listUsersDTO = new ArrayList<>();

        for (User user : listUsers){
            listUsersDTO.add(roomParser.parseTo(user));
        }

        RoomDTO roomDTO = RoomDTO.builder()
                .capacityLimit(capacityRoom)
                .roomName(nameRoom)
                .userHost(userHost)
                .users(listUsersDTO)
                .build();

        roomManager.setRoomList(roomDTO);
        return roomDTO;
    }

    public RoomDTO returnInfoRoom(UUID idRoom) {
        for (RoomDTO room : roomManager.getRoomList()) {
            if (room.getId().equals(idRoom)) {
                return room;
            }
        }
        throw new RuntimeException(EMessage.NO_GET_ROOM_INFORMATION.getMessage());
    }

    public void enterRoom(UserDTO dto, UUID idRoom) {

        for (RoomDTO room : roomManager.getRoomList()) {
            if (room.getId().equals(idRoom)) {
                if (room.getUsers().size() <= room.getCapacityLimit()) room.getUsers().add(dto);
                else throw new RuntimeException(EMessage.NO_ENTER_ROOM.getMessage());
            }
        }
    }

    public void alterRoomHost(ChangeUserDTO dto, UUID idRoom) {

        for (RoomDTO room : roomManager.getRoomList()) {
            if (room.getId().equals(idRoom)) {
                if (room.getUserHost().equals(dto.getUserHost()))
                    room.setUserHost(dto.getUserChange());
                else throw new RuntimeException(EMessage.NO_CHANGE_ROOM_HOST.getMessage());
            }
        }
    }

    public void leaveRoom(UserDTO dto, UUID idRoom) {

        for (RoomDTO room : roomManager.getRoomList()) {
            if (room.getId().equals(idRoom)) {
                if (room.getUsers().contains(dto)) room.getUsers().remove(dto);
                else throw new RuntimeException(EMessage.NO_LEAVE_ROOM.getMessage());
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

    private List<User> getUsers(List<String> users){
        List<User> listUsers = new ArrayList<>();

        for (String user : users){
            User userBase = repository.findByUser(user);
            if (users.isEmpty()){
                continue;
            }
            listUsers.add(userBase);
        }
        return listUsers;
    }
}
