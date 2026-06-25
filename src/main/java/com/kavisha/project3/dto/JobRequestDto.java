package com.kavisha.project3.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class JobRequestDto {
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Company is required")
    private String company;
    @NotBlank(message = "Location is required")
    private String location;
    @NotNull(message = "Salary is required")
    private Double salary;
    @NotBlank(message = "Description is required")
    private String description;
    @NotNull(message = "Recruiter Id is required")
    private Long recruiterId;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getRecruiterId() {
        return recruiterId;
    }

    public void setRecruiterId(Long recruiterId) {
        this.recruiterId = recruiterId;
    }

    public JobRequestDto() {

    }

    public JobRequestDto(String title, String company, String location, Double salary, String description, Long recruiterId) {
        this.title = title;
        this.company = company;
        this.location = location;
        this.salary = salary;
        this.description = description;
        this.recruiterId = recruiterId;
    }
}
