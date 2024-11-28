package com.dipendra.jobms.job.impl;

import com.dipendra.jobms.job.Job;
import com.dipendra.jobms.job.JobRepository;
import com.dipendra.jobms.job.JobService;
import com.dipendra.jobms.job.Mapper.JobMapper;
import com.dipendra.jobms.job.dto.JobDTO;
import com.dipendra.jobms.job.extenal.Company;
import com.dipendra.jobms.job.extenal.Review;
import org.apache.http.client.methods.HttpGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {


    private JobRepository jobRepository;

    @Autowired
    RestTemplate restTemplate;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }


    private JobDTO convertJobToDTO(Job job) {
//        RestTemplate restTemplate = new RestTemplate();
        Company company = restTemplate.getForObject("http://COMPANY-SERVICE:8081/companies/" + job.getCompanyId(), Company.class);

        ResponseEntity<List<Review>> reviewRes = restTemplate.exchange("http://REVIEW-SERVICE:8083/reviews?companyId=" + job.getCompanyId(), HttpMethod.GET,null,new ParameterizedTypeReference<List<Review>>() {
        });

        List<Review> reviews = reviewRes.getBody();
        JobDTO jobDTO = JobMapper.mapJobWithCompanyDto(job,company,reviews );
//        jobDTO.setCompany(company);

        return jobDTO;

    }
    @Override
    public List<JobDTO> findAll() {

        List<Job> jobs = jobRepository.findAll();

        return jobs.stream().map(this::convertJobToDTO).collect(Collectors.toList());
    }

    @Override
    public void createJob(Job job) {
//        job.setId(nextId++);
        jobRepository.save(job);
    }

    @Override
    public JobDTO findJobById(Long id) {

        Job job =  jobRepository.findById(id).orElse(null);
        return convertJobToDTO(job);
    }

    @Override
    public boolean deleteJobById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if(jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setLocation(updatedJob.getLocation());
            jobRepository.save(job);
            return true;
        }
        return false;
    }


}
