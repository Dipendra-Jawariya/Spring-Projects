package com.dipendra.jobApp.job;

import com.dipendra.jobApp.company.Company;
import org.springframework.stereotype.Service;

import java.util.List;


public interface JobService {
    // created this as interface to promote loose coupling

    List<Job> findAll();
    void createJob(Job job);
    Job findJobById(Long id);

    boolean deleteJobById(Long id);

    boolean updateJob(Long id, Job updatedJob);



}
