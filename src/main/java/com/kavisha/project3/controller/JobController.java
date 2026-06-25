package com.kavisha.project3.controller;

import com.kavisha.project3.dto.JobRequestDto;
import com.kavisha.project3.dto.JobResponseDto;
import com.kavisha.project3.service.JobService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private JobService jobService;

    public JobController(JobService jobService){
        this.jobService = jobService;
    }

    @PostMapping
    public JobResponseDto createJob(
           @Valid @RequestBody JobRequestDto request){

        return jobService.createJob(request);
    }

    @GetMapping
    public List<JobResponseDto> getAllJobs(){

        return jobService.getAllJobs();
    }

    @GetMapping("/{id}")
    public JobResponseDto getJobById(
           @PathVariable Long id){

        return jobService.getJobById(id);
    }

    @PutMapping("/{id}")
    public JobResponseDto updateJob(
            @PathVariable Long id,
            @Valid @RequestBody JobRequestDto request){

        return jobService.updateJob(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteJob(
            @PathVariable Long id){

        return jobService.deleteJob(id);
    }
}