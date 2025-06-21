package com.hair.User_Service.servive;

import com.hair.User_Service.Exception.UserException;
import com.hair.User_Service.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User createUser(User user);

    User getUserById(Long id) throws UserException;

    List<User> getAllUser();

    User updateUser(Long id, User user) throws UserException;


    void  deleteUser(Long id) throws UserException;
}
