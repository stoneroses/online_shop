package com.wang.michael.online_shop.service.impl;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wang.michael.online_shop.exception.UserNotFound;
import com.wang.michael.online_shop.model.User;
import com.wang.michael.online_shop.repository.UserRepository;
import com.wang.michael.online_shop.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public User create(User user) {
        User createdUser = user;
        createdUser.setCreatedDateTime(new DateTime());
        return userRepository.save(createdUser);
    }

    @Override
    @Transactional(rollbackFor = UserNotFound.class)
    public User delete(Long id) throws UserNotFound {
        User deletedUser = userRepository.findOne(id);
        if (deletedUser == null) {
            throw new UserNotFound();
        }
        userRepository.delete(deletedUser);
        return deletedUser;
    }

    @Override
    @Transactional
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor = UserNotFound.class)
    public User update(User user) throws UserNotFound {
        User updatedUser = userRepository.findOne(user.getId());
        if (updatedUser == null) {
            throw new UserNotFound();
        }
        updatedUser.setName(user.getName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setDescription(user.getDescription());
        updatedUser.setUpdatedDateTime(new DateTime());
        return updatedUser;
    }

    @Override
    @Transactional
    public User findById(Long id) throws UserNotFound {
        User result = userRepository.findOne(id);
        if (result == null) {
            throw new UserNotFound();
        }
        return result;
    }

    @Override
    public User getByEmail(String email) throws UserNotFound {
        List<User> result = userRepository.findByEmail(email);
        if (result == null) {
            throw new UserNotFound("No user found.");
        } else if (result.size() > 1) {
            throw new UserNotFound("More than one user found. Please contact admin.");
        } else {
            return result.get(0);
        }
    }
}
