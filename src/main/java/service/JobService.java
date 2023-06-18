package service;

import model.Job;
import repository.JobRepository;

import java.util.Date;
import java.util.List;

public class JobService {
    private JobRepository jobRepository = new JobRepository();

    public List<Job> findAllJob() {
        return jobRepository.findAllJob();
    }

    public boolean insertJob(String name, Date start, Date end) {
        return jobRepository.insertJob(name, start, end);
    }
}
