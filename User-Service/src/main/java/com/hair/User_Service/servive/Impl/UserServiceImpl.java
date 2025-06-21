package com.hair.User_Service.servive.Impl;

import com.hair.User_Service.Exception.UserException;
import com.hair.User_Service.Repository.UserRepository;
import com.hair.User_Service.model.User;
import com.hair.User_Service.servive.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    final private UserRepository  userRepository;



    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) throws UserException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()){
            return user.get();
        }
        throw new UserException("User(Find User by ID) not found by that ID"+userId);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }


    @Override
    public User updateUser(Long userId,User user) throws UserException {
        Optional<User> getUser   = userRepository.findById(userId);
        if (getUser.isEmpty()){
            throw new UserException("User(Update User) not found with ID "+ userId);
        }
        User exitingUser = getUser.get();

        exitingUser.setFullName(user.getFullName());
        exitingUser.setEmail(user.getEmail());
        exitingUser.setRole(user.getRole());
        exitingUser.setPhone(user.getPhone());

        return userRepository.save(exitingUser);

    }
    @Override
    public void deleteUser(Long userId) throws UserException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()){
            throw  new UserException("User(Delete User by id) id not present by id;"+userId);
        }
        userRepository.deleteById(user.get().getId());
    }
}

