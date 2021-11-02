package hrms.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hrms.business.abstracts.JobSeekerService;
import hrms.core.utils.results.DataResult;
import hrms.entities.concretes.JobSeeker;

@RestController
@CrossOrigin
@RequestMapping("/api/jobSeekers")
public class JobSeekersController {
    
    private JobSeekerService jobSeekerService;

    public JobSeekersController(JobSeekerService jobSeekerService) {
        this.jobSeekerService = jobSeekerService;
    }

    @GetMapping("/getAll")
    public DataResult<List<JobSeeker>> getAll(){
        return this.jobSeekerService.getAll();
    }

}
