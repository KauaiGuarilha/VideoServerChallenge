package com.videoserverchallenge.controller;

import com.videoserverchallenge.model.dto.*;
import com.videoserverchallenge.model.factory.RoomDTOResponseFactory;
import com.videoserverchallenge.model.factory.UserRoomsDTOResponseFactory;
import com.videoserverchallenge.model.service.RoomService;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired RoomService service;
    @Autowired RoomDTOResponseFactory roomFactory;
    @Autowired UserRoomsDTOResponseFactory userRoomsFactory;

    @Secured({"ROLE_ADMIN"})
    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA)
    public RoomDTOResponse createRoom(
            String nameRoom,
            Integer capacityRoom,
            String host,
            @RequestParam(required = false) List<String> users) {
        return roomFactory.dtoToResponse(service.createRoom(nameRoom, capacityRoom, host, users));
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/enter-room/{idRoom}")
    public ResponseEntity enterRoom(@RequestBody UserDTO dto, @PathVariable("idRoom") UUID idRoom) {
        service.enterRoom(dto, idRoom);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/alter-host/{idRoom}")
    public ResponseEntity alterHost(
            @RequestBody ChangeUserDTO change, @PathVariable("idRoom") UUID idRoom) {
        service.alterRoomHost(change, idRoom);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/leave-room/{idRoom}")
    public ResponseEntity leaveRoom(@RequestBody UserDTO dto, @PathVariable("idRoom") UUID idRoom) {
        service.leaveRoom(dto, idRoom);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/return-room/{idRoom}")
    public RoomDTOResponse returnRoom(@PathVariable("idRoom") UUID idRoom) {
        return roomFactory.dtoToResponse(service.returnInfoRoom(idRoom));
    }

    @GetMapping("/return-user-room/{username}")
    public UserRoomsDTOResponse returnRoom(@PathVariable("username") String username) {
        List<RoomDTO> rooms = service.returnRoomsByUser(username);
        List<RoomDTOResponse> roomsResponse = new ArrayList<>();
        for (RoomDTO room : rooms) {
            roomsResponse.add(roomFactory.dtoToResponse(room));
        }
        return userRoomsFactory.dtoToUserRooms(username, roomsResponse);
    }
}
