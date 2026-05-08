package com.simone.lms.services;

import com.simone.lms.model.User;
import com.simone.lms.payload.dto.UserDTO;

import java.util.List;

public interface UserService {

    public User getCurrentUser() throws Exception;
    public List<UserDTO> getAllUsers();

}
