package com.wang.michael.online_shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wang.michael.online_shop.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT user FROM User user WHERE LOWER(user.email) = LOWER(:email)")
    public List<User> findByEmail(@Param("email") String email);

}
