package com.videoserverchallenge.model.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotBlank(message = "Please enter the name")
    private String name;

    @NotBlank(message = "Please enter the password")
    private String password;

    @NotBlank(message = "Please enter the mobileToken")
    private String mobileToken;
}
