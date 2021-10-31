package hrms.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hrms.business.abstracts.JobPositionsService;
import hrms.entities.concretes.JobPosition;

@RestController
@CrossOrigin
@RequestMapping("/api/jobPositions")
public class JobPositionsController {
    
    private JobPositionsService jobPositionsService;

    public JobPositionsController(JobPositionsService jobPositionsService) {
        this.jobPositionsService = jobPositionsService;
    }

    @GetMapping("/getAll")
    public List<JobPosition> getAll(){
        return this.jobPositionsService.getAll();
    }
}
