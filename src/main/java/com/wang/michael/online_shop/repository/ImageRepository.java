package com.wang.michael.online_shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wang.michael.online_shop.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query("SELECT image FROM Image image WHERE LOWER(image.name) LIKE %:imageName%")
    List<Image> findByName(@Param("imageName") String imageName);

}
