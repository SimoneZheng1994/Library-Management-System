package com.simone.lms.services.impl;

import com.simone.lms.mapper.UserMapper;
import com.simone.lms.model.User;
import com.simone.lms.payload.dto.UserDTO;
import com.simone.lms.repository.UserRepository;
import com.simone.lms.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getCurrentUser() throws Exception {

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByUserName(userName);

        if (user == null) {
            throw new Exception("User not found!");
        }

        return user;
    };

    @Override
    public List<UserDTO> getAllUsers() {

        List<User> userList = userRepository.findAll();


        return userList.stream().map(UserMapper::toDTO).collect(Collectors.toList());
    }

}
