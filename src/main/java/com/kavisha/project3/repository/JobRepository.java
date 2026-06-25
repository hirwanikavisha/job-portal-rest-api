package com.kavisha.project3.repository;

import com.kavisha.project3.entity.Job;
import com.kavisha.project3.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobRepository extends JpaRepository<Job,Long>{
    List<Job> findByRecruiter(User recruiter);
}
