package hrms.api.controllers;

import hrms.business.abstracts.ResumeService;
import hrms.entities.dtos.request.ResumeRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/resumes")
public class ResumesController {

    private final ResumeService resumeService;

    public ResumesController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody ResumeRequestDto resume) {
        return ResponseEntity.ok(this.resumeService.add(resume));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(this.resumeService.getAll());
    }

}
