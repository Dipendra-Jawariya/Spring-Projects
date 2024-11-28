package com.dipendra.jobms.job;

import com.dipendra.jobms.job.dto.JobDTO;

import java.util.List;


public interface JobService {
    // created this as interface to promote loose coupling

    List<JobDTO> findAll();
    void createJob(Job job);
    JobDTO findJobById(Long id);

    boolean deleteJobById(Long id);

    boolean updateJob(Long id, Job updatedJob);



}
