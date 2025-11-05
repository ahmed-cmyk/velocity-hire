package com.velocity.velocityhire.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.velocity.velocityhire.entity.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByPostedById(Long postedById);
    List<Job> findByCategory(String category);
    List<Job> findByStatus(String status);
    List<Job> findByCategoryAndStatus(String category, String status);
}