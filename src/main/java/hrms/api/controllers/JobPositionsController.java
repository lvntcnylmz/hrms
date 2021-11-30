package hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hrms.business.abstracts.JobPositionsService;
import hrms.entities.concretes.JobPosition;

@RestController
@CrossOrigin
@RequestMapping("/api/jobPositions")
public class JobPositionsController {
    
    private JobPositionsService jobPositionsService;

    @Autowired
    public JobPositionsController(JobPositionsService jobPositionsService) {
        this.jobPositionsService = jobPositionsService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody JobPosition jobPosition){
        return ResponseEntity.ok(this.jobPositionsService.add(jobPosition));
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.jobPositionsService.getAll());
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam int jobId){
        return ResponseEntity.ok(this.jobPositionsService.getById(jobId));
    }

}
