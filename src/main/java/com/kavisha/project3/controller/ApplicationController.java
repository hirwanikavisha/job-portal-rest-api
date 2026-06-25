
package com.kavisha.project3.controller;

import com.kavisha.project3.dto.ApplicationRequestDto;
import com.kavisha.project3.dto.ApplicationResponseDto;
import com.kavisha.project3.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    private ApplicationService applicationService;

    public ApplicationController(
            ApplicationService applicationService){

        this.applicationService = applicationService;
    }

    @PostMapping
    public ApplicationResponseDto applyForJob(
            @Valid @RequestBody ApplicationRequestDto request){

        return applicationService.applyForJob(request);
    }

    @GetMapping
    public List<ApplicationResponseDto>
    getAllApplications(){

        return applicationService.getAllApplications();
    }

    @GetMapping("/{id}")
    public ApplicationResponseDto getApplicationById(
            @PathVariable Long id){

        return applicationService.getApplicationById(id);
    }

    @GetMapping("/candidate/{candidateId}")
    public List<ApplicationResponseDto>
    getApplicationsByCandidate(
            @PathVariable Long candidateId){

        return applicationService
                .getApplicationsByCandidate(candidateId);
    }

    @GetMapping("/job/{jobId}")
    public List<ApplicationResponseDto>
    getApplicationsByJob(
            @PathVariable Long jobId){

        return applicationService
                .getApplicationsByJob(jobId);
    }

    @DeleteMapping("/{id}")
    public String deleteApplication(
            @PathVariable Long id){

        return applicationService.deleteApplication(id);
    }
}