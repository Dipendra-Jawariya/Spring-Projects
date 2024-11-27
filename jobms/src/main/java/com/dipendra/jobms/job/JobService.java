package com.dipendra.jobms.job;

import com.dipendra.jobms.job.dto.JobWithCompanyDTO;

import java.util.List;


public interface JobService {
    // created this as interface to promote loose coupling

    List<JobWithCompanyDTO> findAll();
    void createJob(Job job);
    Job findJobById(Long id);

    boolean deleteJobById(Long id);

    boolean updateJob(Long id, Job updatedJob);



}
