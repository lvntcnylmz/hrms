package hrms.api.controllers;

import hrms.business.abstracts.JobSeekerService;
import hrms.entities.concretes.JobSeeker;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/jobSeekers")
public class JobSeekersController {

    private final JobSeekerService jobSeekerService;

    public JobSeekersController(JobSeekerService jobSeekerService) {
        this.jobSeekerService = jobSeekerService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody JobSeeker jobSeeker) {
        return ResponseEntity.ok(this.jobSeekerService.add(jobSeeker));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(this.jobSeekerService.delete(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(this.jobSeekerService.getAll());
    }
}
