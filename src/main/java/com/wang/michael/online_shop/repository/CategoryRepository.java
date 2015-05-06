package com.wang.michael.online_shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wang.michael.online_shop.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT category FROM Category category WHERE LOWER(category.name) LIKE %:categoryName%")
    List<Category> findByName(@Param("categoryName") String categoryName);

    @Query("SELECT category FROM Category category WHERE category.parent = :parentId")
    List<Category> findAllChildrenCategories(@Param("parentId") Integer parentId);

    @Query("SELECT category FROM Category category WHERE category.parent IS NULL")
    List<Category> findAllTopCategory();

}
