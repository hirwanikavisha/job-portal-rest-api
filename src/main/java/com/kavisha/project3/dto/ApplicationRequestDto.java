package com.kavisha.project3.dto;

import jakarta.validation.constraints.NotNull;

public class ApplicationRequestDto {
    @NotNull(message = "Job Id is required")
    private Long jobId;
    @NotNull(message = "Candidate Id is required")
    private Long candidateId;

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public ApplicationRequestDto() {

    }

    public ApplicationRequestDto(Long jobId, Long candidateId) {
        this.jobId = jobId;
        this.candidateId = candidateId;
    }
}
