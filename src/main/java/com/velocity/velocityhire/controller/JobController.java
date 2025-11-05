package com.velocity.velocityhire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.velocity.velocityhire.dto.JobCreateDTO;
import com.velocity.velocityhire.dto.JobDTO;
import com.velocity.velocityhire.dto.JobUpdateDTO;
import com.velocity.velocityhire.entity.Job;
import com.velocity.velocityhire.enums.JobStatus;
import com.velocity.velocityhire.service.JobService;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<JobDTO>> getJobs() {
        List<Job> jobs = jobService.findAll();
        List<JobDTO> jobDTOs = jobs.stream()
            .map(job -> JobDTO.builder()
                .id(job.getId())
                .title(job.getTitle())
                .description(job.getDescription())
                .category(job.getCategory())
                .budget(job.getBudget())
                .hourlyRate(job.getHourlyRate())
                .status(JobStatus.valueOf(job.getStatus()))
                .postedById(job.getPostedById())
                .createdAt(job.getCreatedAt())
                .updatedAt(job.getUpdatedAt())
                .build())
            .collect(java.util.stream.Collectors.toList());
        
        return ResponseEntity.ok(jobDTOs);
    }

    @GetMapping("{id}")
    public ResponseEntity<JobDTO> getJob(@RequestParam Long id) {
        Job job = jobService.findById(id);
        JobDTO jobDTO = JobDTO.builder()
            .id(job.getId())
            .title(job.getTitle())
            .description(job.getDescription())
            .category(job.getCategory())
            .budget(job.getBudget())
            .hourlyRate(job.getHourlyRate())
            .status(JobStatus.valueOf(job.getStatus()))
            .postedById(job.getPostedById())
            .createdAt(job.getCreatedAt())
            .updatedAt(job.getUpdatedAt())
            .build();

        return ResponseEntity.ok(jobDTO);
    }

    @PostMapping
    public ResponseEntity<JobDTO> createJob(JobCreateDTO jobCreateDTO) {
        Job job = jobService.save(jobCreateDTO);
        JobDTO jobDTO = JobDTO.builder()
            .id(job.getId())
            .title(job.getTitle())
            .description(job.getDescription())
            .category(job.getCategory())
            .budget(job.getBudget())
            .hourlyRate(job.getHourlyRate())
            .status(JobStatus.valueOf(job.getStatus()))
            .postedById(job.getPostedById())
            .createdAt(job.getCreatedAt())
            .updatedAt(job.getUpdatedAt())
            .build();

        return ResponseEntity.ok(jobDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity<JobDTO> updateJob(@PathVariable("id") long id, JobUpdateDTO jobUpdateDTO) {
        Job job = jobService.update(id, jobUpdateDTO);
        JobDTO jobDTO = JobDTO.builder()
            .id(job.getId())
            .title(job.getTitle())
            .description(job.getDescription())
            .category(job.getCategory())
            .budget(job.getBudget())
            .hourlyRate(job.getHourlyRate())
            .status(JobStatus.valueOf(job.getStatus()))
            .postedById(job.getPostedById())
            .createdAt(job.getCreatedAt())
            .updatedAt(job.getUpdatedAt())
            .build();

        return ResponseEntity.ok(jobDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteJob(@PathVariable("id") long id) {
        boolean jobDeleted = jobService.delete(id);
        if (!jobDeleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Job deleted successfully");
    }
}