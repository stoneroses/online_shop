package com.wang.michael.online_shop.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wang.michael.online_shop.exception.UserNotFound;
import com.wang.michael.online_shop.model.User;

public interface UserService {

    public User create(User user);

    public User delete(Long id) throws UserNotFound;

    public List<User> findAll();

    public User update(User user) throws UserNotFound;

    public User findById(Long id) throws UserNotFound;

    public User getByEmail(String email) throws UserNotFound;

    public User savePassword(String email, String password) throws UserNotFound;

    public User save(User user);

    public User savePassword(Long id, String password) throws UserNotFound;

    public Page<User> getUsers(int page, int size);

}
