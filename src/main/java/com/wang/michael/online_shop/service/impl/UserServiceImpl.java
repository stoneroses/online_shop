package com.wang.michael.online_shop.service.impl;

import java.util.List;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        createdUser.setPassword(getEncryptPassword(user.getPassword(), user.getEmail()));
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
        } else if (result.size() == 0) {
            throw new UserNotFound("No user found.");
        } else {
            return result.get(0);
        }
    }

    private String getEncryptPassword(String password, String salt) {
        return new Sha256Hash(password, salt).toString();
    }

    @Override
    @Transactional
    public User savePassword(String email, String password) throws UserNotFound {
        User user = getByEmail(email);
        user.setPassword(getEncryptPassword(password, email));
        return userRepository.save(user);
    }

    @Override
    @Transactional(rollbackFor = UserNotFound.class)
    public User save(User user) {
        if (user.getId() != null) {
            User oldUser = userRepository.findOne(user.getId());
            user.setCreatedDateTime(oldUser.getCreatedDateTime());
            user.setUpdatedDateTime(new DateTime());
            user.setPassword(oldUser.getPassword());
        } else {
            user.setCreatedDateTime(new DateTime());
            user.setPassword("password");
        }
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User savePassword(Long id, String password) throws UserNotFound {
        User user = findById(id);
        user.setPassword(getEncryptPassword(password, user.getEmail()));
        return userRepository.save(user);
    }

    @Override
    public Page<User> getUsers(int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        return userRepository.findAll(pageable);
    }
}
