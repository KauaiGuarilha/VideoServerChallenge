package com.videoserverchallenge.model.factory;

import com.videoserverchallenge.model.dto.UserDTO;
import com.videoserverchallenge.model.dto.UserDTOResponse;
import com.videoserverchallenge.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserDTOResponseFactory {

    public UserDTOResponse userToResponse(User user) {
        return UserDTOResponse.builder()
                .name(user.getName())
                .password(user.getPassword())
                .mobileToken(user.getMobileToken())
                .build();
    }

    public User toUser(UserDTO dto) {
        return User.builder()
                .name(dto.getName())
                .password(dto.getPassword())
                .mobileToken(dto.getMobileToken())
                .build();
    }

    public UserDTO toUserDTO(User dto) {
        return UserDTO.builder()
                .name(dto.getName())
                .password(dto.getPassword())
                .mobileToken(dto.getMobileToken())
                .build();
    }
}
