package com.velocity.velocityhire.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.velocity.velocityhire.dto.JobCreateDTO;
import com.velocity.velocityhire.dto.JobUpdateDTO;
import com.velocity.velocityhire.entity.Job;
import com.velocity.velocityhire.enums.JobStatus;
import com.velocity.velocityhire.repository.JobRepository;

@Service
public class JobService {

    private final JobRepository jobRepository;

    @Autowired
    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public Job save(JobCreateDTO jobCreateDTO) {
        Job job = Job.builder()
            .title(jobCreateDTO.getTitle())
            .description(jobCreateDTO.getDescription())
            .category(jobCreateDTO.getCategory())
            .budget(jobCreateDTO.getBudget())
            .hourlyRate(jobCreateDTO.getHourlyRate())
            .status(JobStatus.OPEN.name())
            .build();

        if (job == null) {
            throw new IllegalArgumentException("Job cannot be null");
        }

        return jobRepository.save(job);
    }

    public Job update(long id, JobUpdateDTO jobUpdateDTO) {
        Optional<Job> jobOptional = jobRepository.findById(id);

        if (jobOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found");
        }

        Job job = jobOptional.get();

        if (jobUpdateDTO.getTitle() != null) {
            job.setTitle(jobUpdateDTO.getTitle());
        }
        
        if (jobUpdateDTO.getDescription() != null) {
            job.setDescription(jobUpdateDTO.getDescription());
        }
        
        if (jobUpdateDTO.getCategory() != null) {
            job.setCategory(jobUpdateDTO.getCategory());
        }
        
        if (jobUpdateDTO.getBudget() != null) {
            job.setBudget(jobUpdateDTO.getBudget());
        }
        
        if (jobUpdateDTO.getHourlyRate() != null) {
            job.setHourlyRate(jobUpdateDTO.getHourlyRate());
        }
        
        if (jobUpdateDTO.getStatus() != null) {
            job.setStatus(jobUpdateDTO.getStatus());
        }
        
        return jobRepository.save(job);
    }

    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    public Job findById(long id) {
        return jobRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found with id: " + id));
    }

    public List<Job> findByPostedById(Long userId) {
        return jobRepository.findByPostedById(userId);
    }

    public List<Job> findByCategory(String category) {
        return jobRepository.findByCategory(category);
    }

    public List<Job> findByStatus(String status) {
        return jobRepository.findByStatus(status);
    }

    public boolean delete(long id) {
        if (jobRepository.existsById(id)) {
            jobRepository.deleteById(id);
            return true;
        }
        return false;
    }
}