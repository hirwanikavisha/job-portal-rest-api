package com.kavisha.project3.service;

import com.kavisha.project3.dto.JobRequestDto;
import com.kavisha.project3.dto.JobResponseDto;
import com.kavisha.project3.entity.Job;
import com.kavisha.project3.entity.User;
import com.kavisha.project3.exception.ResourceNotFoundException;
import com.kavisha.project3.repository.JobRepository;
import com.kavisha.project3.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobService {

    private JobRepository jobRepository;
    private UserRepository userRepository;

    public JobService(JobRepository jobRepository,
                      UserRepository userRepository) {

        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    // Create Job
    public JobResponseDto createJob(JobRequestDto request) {

        User recruiter = userRepository.findById(
                        request.getRecruiterId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Recruiter not found"));

        Job job = new Job();

        job.setTitle(request.getTitle());
        job.setCompany(request.getCompany());
        job.setLocation(request.getLocation());
        job.setSalary(request.getSalary());
        job.setDescription(request.getDescription());
        job.setRecruiter(recruiter);

        Job savedJob = jobRepository.save(job);

        return mapToResponse(savedJob);
    }

    // Get All Jobs
    public List<JobResponseDto> getAllJobs() {

        List<Job> jobs = jobRepository.findAll();

        List<JobResponseDto> response = new ArrayList<>();

        for(Job job : jobs){
            response.add(mapToResponse(job));
        }

        return response;
    }

    // Get Job By Id
    public JobResponseDto getJobById(Long id){

        Job job = jobRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job not found"));

        return mapToResponse(job);
    }

    // Update Job
    public JobResponseDto updateJob(Long id,
                                    JobRequestDto request){

        Job job = jobRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job not found"));

        User recruiter = userRepository.findById(
                        request.getRecruiterId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Recruiter not found"));

        job.setTitle(request.getTitle());
        job.setCompany(request.getCompany());
        job.setLocation(request.getLocation());
        job.setSalary(request.getSalary());
        job.setDescription(request.getDescription());
        job.setRecruiter(recruiter);

        Job updatedJob = jobRepository.save(job);

        return mapToResponse(updatedJob);
    }

    // Delete Job
    public String deleteJob(Long id){

        Job job = jobRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job not found"));

        jobRepository.delete(job);

        return "Job deleted successfully";
    }

    // Mapper Method
    private JobResponseDto mapToResponse(Job job){

        JobResponseDto response =
                new JobResponseDto();

        response.setId(job.getId());
        response.setTitle(job.getTitle());
        response.setCompany(job.getCompany());
        response.setLocation(job.getLocation());
        response.setSalary(job.getSalary());
        response.setDescription(job.getDescription());
        response.setRecruiterId(job.getRecruiter().getId());
        response.setRecruiterName(job.getRecruiter().getName());
        return response;
    }
}