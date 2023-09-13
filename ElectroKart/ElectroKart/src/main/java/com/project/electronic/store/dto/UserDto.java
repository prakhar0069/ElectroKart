package com.project.electronic.store.dto;

import com.project.electronic.store.validate.ImageNameValid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserDto {

    private String userId;

    @Size(min=3,max=20,message = "Invalid name !!")
    private String name;

    //@Email(message = "Invalid User Email !!")
    @Pattern(regexp=  "^[a-z0-9][-a-z0-9._]+@([-a-z0-9]+\\.)+[a-z]{2,5}$",message="Invalid User Email !!")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required !!")
    private String password;

    @Size(min=4,max=6,message="Invalid gender !!")
    private String gender;

    @NotBlank(message = "Write something about yourself !!")
    private String about;

    @ImageNameValid
    private String imageName;
}
