package com.videoserverchallenge.controller;

import com.videoserverchallenge.model.dto.UserDTO;
import com.videoserverchallenge.model.dto.UserDTOResponse;
import com.videoserverchallenge.model.factory.UserDTOResponseFactory;
import com.videoserverchallenge.model.service.UserService;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Tag(
        name = "User Controller",
        description = "User information",
        externalDocs = @ExternalDocumentation(description = "Open section"))
public class UserController {

    @Autowired private UserService service;
    @Autowired private UserDTOResponseFactory factory;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON)
    @Operation(summary = "Save user", description = "Information about the creation of a user.")
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
    public ResponseEntity saveUser(@RequestBody UserDTO dto) {
        UserDTOResponse response = factory.userToResponse(service.saveUser(factory.toUser(dto)));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update user", description = "Information from user change.")
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
    public UserDTOResponse updateUser(@PathVariable String id, @RequestBody UserDTO dto) {
        return factory.userToResponse(service.update(factory.toUser(dto), id));
    }

    @GetMapping("/return/{username}")
    @Operation(summary = "Return user", description = "Information from user return.")
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
    public UserDTOResponse ReturnUser(@NotBlank @PathVariable String username) {
        return factory.userToResponse(service.returnUser(username));
    }

    @GetMapping("/return")
    @Operation(summary = "Return user", description = "Information from user return.")
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
    public UserDTOResponse ReturnUserQueryParam(@NotBlank @RequestParam String username) {
        return factory.userToResponse(service.returnUser(username));
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/return-list")
    @Operation(summary = "Return list users", description = "Information from return user list.")
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
    public List<UserDTOResponse> ReturnListUsers() {
        return service.returnListUser().stream()
                .map(factory::userToResponse)
                .collect(Collectors.toList());
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete user", description = "Information about user removal.")
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
    public ResponseEntity deleteUser(@PathVariable String id) {
        service.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
