package com.kavisha.project3.service;

import com.kavisha.project3.dto.ApplicationRequestDto;
import com.kavisha.project3.dto.ApplicationResponseDto;
import com.kavisha.project3.entity.Application;
import com.kavisha.project3.entity.Job;
import com.kavisha.project3.entity.User;
import com.kavisha.project3.exception.ResourceNotFoundException;
import com.kavisha.project3.repository.ApplicationRepository;
import com.kavisha.project3.repository.JobRepository;
import com.kavisha.project3.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationService {

    private ApplicationRepository applicationRepository;
    private JobRepository jobRepository;
    private UserRepository userRepository;

    public ApplicationService(ApplicationRepository applicationRepository,
                              JobRepository jobRepository,
                              UserRepository userRepository) {

        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    // Apply for Job
    public ApplicationResponseDto applyForJob(
            ApplicationRequestDto request) {

        Job job = jobRepository.findById(
                        request.getJobId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job not found"));

        User candidate = userRepository.findById(
                        request.getCandidateId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Candidate not found"));

        Application application = new Application();

        application.setJob(job);
        application.setCandidate(candidate);
        application.setStatus("APPLIED");
        application.setApplied_at(LocalDateTime.now());

        Application savedApplication =
                applicationRepository.save(application);

        return mapToResponse(savedApplication);
    }

    // Get All Applications
    public List<ApplicationResponseDto> getAllApplications() {

        List<Application> applications =
                applicationRepository.findAll();

        List<ApplicationResponseDto> response =
                new ArrayList<>();

        for (Application application : applications) {
            response.add(mapToResponse(application));
        }

        return response;
    }

    // Get Application By Id
    public ApplicationResponseDto getApplicationById(
            Long id) {

        Application application =
                applicationRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Application not found"));

        return mapToResponse(application);
    }

    // Get Applications By Candidate
    public List<ApplicationResponseDto>
    getApplicationsByCandidate(Long candidateId) {

        User candidate = userRepository.findById(
                        candidateId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Candidate not found"));

        List<Application> applications =
                applicationRepository.findByCandidate(
                        candidate);

        List<ApplicationResponseDto> response =
                new ArrayList<>();

        for (Application application : applications) {
            response.add(mapToResponse(application));
        }

        return response;
    }

    // Get Applications By Job
    public List<ApplicationResponseDto>
    getApplicationsByJob(Long jobId) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Job not found"));

        List<Application> applications =
                applicationRepository.findByJob(job);

        List<ApplicationResponseDto> response =
                new ArrayList<>();

        for (Application application : applications) {
            response.add(mapToResponse(application));
        }

        return response;
    }

    // Delete Application
    public String deleteApplication(Long id) {

        Application application =
                applicationRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Application not found"));

        applicationRepository.delete(application);

        return "Application deleted successfully";
    }

    // Mapper
    private ApplicationResponseDto mapToResponse(
            Application application) {

        ApplicationResponseDto response =
                new ApplicationResponseDto();

        response.setId(application.getId());

        response.setJobId(application.getJob().getId());

        response.setJobTitle(application.getJob().getTitle());

        response.setCandidateId(application.getCandidate().getId());

        response.setCandidateName(application.getCandidate().getName());

        response.setStatus(application.getStatus());

        return response;
    }
}