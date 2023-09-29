package com.project.electronic.store.services;

import com.project.electronic.store.dto.PageableResponse;
import com.project.electronic.store.dto.UserDto;
import com.project.electronic.store.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto,String userId);

    void deleteUser(String userId);

    PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir);

    UserDto getUserById(String userId);

    UserDto getUserByEmail(String email);

    List<UserDto> searchUser(String keyword);

    Optional<User> findUserByEmailOptional(String email);

}
