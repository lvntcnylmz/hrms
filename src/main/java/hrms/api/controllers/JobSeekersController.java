package hrms.api.controllers;

import hrms.business.abstracts.JobSeekerService;
import hrms.core.utils.results.DataResult;
import hrms.entities.concretes.JobSeeker;
import hrms.entities.dtos.request.JobSeekerRegisterDto;
import hrms.entities.dtos.response.JobSeekerResponseDto;
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
        return ResponseEntity.ok(this.jobSeekerService.getAll().getData()
                .stream()
                .map(jobSeeker -> this.modelMapper.map(jobSeeker, JobSeekerResponseDto.class)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        DataResult<JobSeeker> jobSeeker = this.jobSeekerService.getById(id);
        JobSeekerResponseDto jobSeekerResponse = this.modelMapper.map(jobSeeker.getData(), JobSeekerResponseDto.class);
        return ResponseEntity.ok().body(jobSeekerResponse);
    }
}
