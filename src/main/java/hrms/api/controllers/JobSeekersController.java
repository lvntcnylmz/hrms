package hrms.api.controllers;

import hrms.business.abstracts.JobSeekerService;
import hrms.entities.concretes.JobSeeker;
import hrms.entities.dtos.request.JobSeekerRegisterDto;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/jobSeekers")
public class JobSeekersController {

    private final JobSeekerService jobSeekerService;
    private final ModelMapper modelMapper;

    public JobSeekersController(JobSeekerService jobSeekerService, ModelMapper modelMapper) {
        this.jobSeekerService = jobSeekerService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody JobSeekerRegisterDto jobSeeker) {
        JobSeeker jobSeekerRegister = this.modelMapper.map(jobSeeker, JobSeeker.class);
        return ResponseEntity.ok(this.jobSeekerService.add(jobSeekerRegister));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(this.jobSeekerService.delete(id));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(this.jobSeekerService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(this.jobSeekerService.getById(id));
    }
}
