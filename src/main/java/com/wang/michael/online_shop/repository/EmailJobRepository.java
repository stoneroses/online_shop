package com.wang.michael.online_shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wang.michael.online_shop.model.EmailJob;

@Repository
public interface EmailJobRepository extends JpaRepository<EmailJob, Long> {

    @Query("SELECT emailJob FROM EmailJob emailJob WHERE LOWER(emailJob.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(emailJob.content) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    public List<EmailJob> findByTitleOrContent(@Param("searchTerm") String term);

}
