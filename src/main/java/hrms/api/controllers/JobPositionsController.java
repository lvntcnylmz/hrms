package hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import hrms.business.abstracts.JobPositionsService;
import hrms.entities.concretes.JobPosition;

@RestController
@CrossOrigin
@RequestMapping("/api/jobPositions")
public class JobPositionsController {

    private final JobPositionsService jobPositionsService;

    @Autowired
    public JobPositionsController(JobPositionsService jobPositionsService) {
        this.jobPositionsService = jobPositionsService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody JobPosition jobPosition) {
        return ResponseEntity.ok(this.jobPositionsService.add(jobPosition));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(this.jobPositionsService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(this.jobPositionsService.getById(id));
    }

}
