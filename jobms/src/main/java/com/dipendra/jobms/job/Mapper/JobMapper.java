package com.dipendra.jobms.job.Mapper;

import com.dipendra.jobms.job.Job;
import com.dipendra.jobms.job.dto.JobDTO;
import com.dipendra.jobms.job.extenal.Company;
import com.dipendra.jobms.job.extenal.Review;

import java.util.List;

public class JobMapper {
    public static JobDTO mapJobWithCompanyDto(
            Job job,
            Company company, List<Review> reviews){
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
//        jobWithCompanyDTO.setCompany(y);
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setMaxSalary(job.getMinSalary());
        jobDTO.setCompany(company);
        jobDTO.setReviews(reviews);
        return jobDTO;
    }
}
