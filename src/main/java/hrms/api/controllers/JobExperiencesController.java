package hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hrms.business.abstracts.JobExperienceService;
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
    public ResponseEntity<?> add(@RequestBody JobExperience jobExperience){
        return ResponseEntity.ok(this.jobExperienceService.add(jobExperience));
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.jobExperienceService.getAll());
    }

}
