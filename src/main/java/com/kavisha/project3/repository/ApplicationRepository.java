package com.kavisha.project3.repository;

import com.kavisha.project3.entity.Application;
import com.kavisha.project3.entity.Job;
import com.kavisha.project3.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application,Long>{
    List<Application> findByJob(Job job);
    List<Application> findByCandidate(User candidate);
}
