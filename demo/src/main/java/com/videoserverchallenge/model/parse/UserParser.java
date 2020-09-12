package com.videoserverchallenge.model.parse;

import com.videoserverchallenge.model.dto.UserDTO;
import com.videoserverchallenge.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserParser {

    public UserDTO parseTo(User user){
        return UserDTO.builder()
                .name(user.getName())
                .password(user.getPassword())
                .mobileToken(user.getMobileToken())
                .build();
    }
}
