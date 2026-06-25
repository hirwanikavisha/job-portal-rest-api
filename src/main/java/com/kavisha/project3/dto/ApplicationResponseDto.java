package com.kavisha.project3.dto;

public class ApplicationResponseDto {
    private Long id;
    private String status;
    private Long jobId;
    private String jobTitle;
    private Long candidateId;
    private String candidateName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public ApplicationResponseDto() {

    }

    public ApplicationResponseDto(Long id, String status, Long jobId, String jobTitle, Long candidateId, String candidateName) {
        this.id = id;
        this.status = status;
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.candidateId = candidateId;
        this.candidateName = candidateName;
    }
}
