package hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hrms.business.abstracts.JobExperienceService;
import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.Result;
import hrms.entities.concretes.JobExperience;

@RestController
@CrossOrigin
@RequestMapping("api/jobExperience")
public class JobExperiencesController {
    
    private JobExperienceService jobExperienceService;

    @Autowired
    public JobExperiencesController(JobExperienceService jobExperienceService) {
        this.jobExperienceService = jobExperienceService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody JobExperience jobExperience){
        return this.jobExperienceService.add(jobExperience);
    }

    @GetMapping("/getAll")
    public DataResult<List<JobExperience>> getAll(){
        return this.jobExperienceService.getAll();
    }

}