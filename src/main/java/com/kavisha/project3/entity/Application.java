package com.kavisha.project3.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="applications")
public class Application {
     @Id
     @GeneratedValue(strategy= GenerationType.IDENTITY)
     private Long id;
     private String status;
     private LocalDateTime applied_at;
     @ManyToOne
     @JoinColumn(name="job_id")
     private Job job;
     @ManyToOne
     @JoinColumn(name="candidate_id")
     private User candidate;

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

    public LocalDateTime getApplied_at() {
        return applied_at;
    }

    public void setApplied_at(LocalDateTime applied_at) {
        this.applied_at = applied_at;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public User getCandidate() {
        return candidate;
    }

    public void setCandidate(User candidate) {
        this.candidate = candidate;
    }

    public Application() {

    }

    public Application(Long id, String status, LocalDateTime applied_at, Job job, User candidate) {
        this.id = id;
        this.status = status;
        this.applied_at = applied_at;
        this.job = job;
        this.candidate = candidate;
    }
}
