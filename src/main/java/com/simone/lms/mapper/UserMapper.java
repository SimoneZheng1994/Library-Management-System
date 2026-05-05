package com.simone.lms.mapper;

import com.simone.lms.model.User;
import com.simone.lms.payload.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserMapper {

    public static UserDTO toDTO(User user) {

        UserDTO userDTO = new UserDTO();

        userDTO.setUserName(user.getUserName());
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setFullName(user.getFullName());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setLastLogin(user.getLastLogin());
        userDTO.setRole(user.getRole());

        return userDTO;

    }

    public static List<UserDTO> toDTOList(List<User> userList) {
        return userList.stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static Set<UserDTO> toDTOSet (Set<User> userSet) {
        return userSet.stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toSet());
    }

    public static User toEntity(UserDTO userDTO) {

        User createdUser = new User();

        createdUser.setUserName(userDTO.getUserName());
        createdUser.setEmail(userDTO.getEmail());
        createdUser.setPassword(userDTO.getPassword());
        createdUser.setCreatedAt(LocalDateTime.now());
        createdUser.setPhoneNumber(userDTO.getPhoneNumber());
        createdUser.setPhoneNumber(userDTO.getPhoneNumber());
        createdUser.setFullName(userDTO.getFullName());
        createdUser.setRole(userDTO.getRole());

        return createdUser;

    }
}

