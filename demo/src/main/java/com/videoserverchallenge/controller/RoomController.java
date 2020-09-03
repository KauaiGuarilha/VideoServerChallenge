package com.videoserverchallenge.controller;

import com.videoserverchallenge.model.dto.*;
import com.videoserverchallenge.model.factory.RoomDTOResponseFactory;
import com.videoserverchallenge.model.factory.UserRoomsDTOResponseFactory;
import com.videoserverchallenge.model.service.RoomService;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room")
@Tag(
        name = "Room Controller",
        description = "Room information.",
        externalDocs = @ExternalDocumentation(description = "Open section"))
public class RoomController {

    @Autowired RoomService service;
    @Autowired RoomDTOResponseFactory roomFactory;
    @Autowired UserRoomsDTOResponseFactory userRoomsFactory;

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/create")
    @Operation(summary = "Create Room", description = "Information about the creation of a room.")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Ok",
                        content =
                                @Content(
                                        schema = @Schema(implementation = UserDetails.class),
                                        mediaType = "application/json")),
                @ApiResponse(
                        responseCode = "400",
                        description = "Bad Request",
                        content =
                                @Content(
                                        schema = @Schema(implementation = UserDetails.class),
                                        mediaType = "application/json"))
            })
    public RoomDTOResponse createRoom(@RequestBody RoomDTO dto) {
        return roomFactory.dtoToResponse(service.createRoom(dto));
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/enter-room/{idRoom}")
    @Operation(summary = "Enter Room", description = "Information about entering a room.")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Ok",
                        content =
                                @Content(
                                        schema = @Schema(implementation = UserDetails.class),
                                        mediaType = "application/json")),
                @ApiResponse(
                        responseCode = "400",
                        description = "Bad Request",
                        content =
                                @Content(
                                        schema = @Schema(implementation = UserDetails.class),
                                        mediaType = "application/json"))
            })
    public ResponseEntity enterRoom(@RequestBody UserDTO dto, @PathVariable("idRoom") UUID idRoom) {
        service.enterRoom(dto, idRoom);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/alter-host/{idRoom}")
    @Operation(summary = "Alter host", description = "Change host from room.")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Ok",
                        content =
                                @Content(
                                        schema = @Schema(implementation = UserDetails.class),
                                        mediaType = "application/json")),
                @ApiResponse(
                        responseCode = "400",
                        description = "Bad Request",
                        content =
                                @Content(
                                        schema = @Schema(implementation = UserDetails.class),
                                        mediaType = "application/json"))
            })
    public ResponseEntity alterHost(
            @RequestBody ChangeUserDTO change, @PathVariable("idRoom") UUID idRoom) {
        service.alterRoomHost(change, idRoom);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/leave-room/{idRoom}")
    @Operation(summary = "Leave host", description = "Information about leaving the room.")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Ok",
                        content =
                                @Content(
                                        schema = @Schema(implementation = UserDetails.class),
                                        mediaType = "application/json")),
                @ApiResponse(
                        responseCode = "400",
                        description = "Bad Request",
                        content =
                                @Content(
                                        schema = @Schema(implementation = UserDetails.class),
                                        mediaType = "application/json"))
            })
    public ResponseEntity leaveRoom(@RequestBody UserDTO dto, @PathVariable("idRoom") UUID idRoom) {
        service.leaveRoom(dto, idRoom);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/return-room/{idRoom}")
    @Operation(summary = "Return room", description = "Return room information.")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Ok",
                        content =
                                @Content(
                                        schema = @Schema(implementation = UserDetails.class),
                                        mediaType = "application/json")),
                @ApiResponse(
                        responseCode = "400",
                        description = "Bad Request",
                        content =
                                @Content(
                                        schema = @Schema(implementation = UserDetails.class),
                                        mediaType = "application/json"))
            })
    public RoomDTOResponse returnRoom(@PathVariable("idRoom") UUID idRoom) {
        return roomFactory.dtoToResponse(service.returnInfoRoom(idRoom));
    }

    @GetMapping("/return-user-room/{username}")
    @Operation(summary = "Return user room", description = "Return user information inside a room.")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Ok",
                        content =
                                @Content(
                                        schema = @Schema(implementation = UserDetails.class),
                                        mediaType = "application/json")),
                @ApiResponse(
                        responseCode = "400",
                        description = "Bad Request",
                        content =
                                @Content(
                                        schema = @Schema(implementation = UserDetails.class),
                                        mediaType = "application/json"))
            })
    public UserRoomsDTOResponse returnRoom(@PathVariable("username") String username) {
        List<RoomDTO> rooms = service.returnRoomsByUser(username);
        List<RoomDTOResponse> roomsResponse = new ArrayList<>();
        for (RoomDTO room : rooms) {
            roomsResponse.add(roomFactory.dtoToResponse(room));
        }
        return userRoomsFactory.dtoToUserRooms(username, roomsResponse);
    }
}
