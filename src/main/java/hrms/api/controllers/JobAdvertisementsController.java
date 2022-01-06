package hrms.api.controllers;

import hrms.business.abstracts.JobAdvertisementService;
import hrms.entities.dtos.request.JobAdvertisementRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/api/jobAdvertisements")
public class JobAdvertisementsController {

    private final JobAdvertisementService jobAdvertisementService;

    public JobAdvertisementsController(JobAdvertisementService jobAdvertisementService) {
        this.jobAdvertisementService = jobAdvertisementService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody JobAdvertisementRequestDto jobAdvertisement) {
        return ResponseEntity.ok(this.jobAdvertisementService.add(jobAdvertisement));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(this.jobAdvertisementService.getAll());
    }

    @GetMapping("/getByJobStatus")
    public ResponseEntity<?> getByJobStatus() {
        return ResponseEntity.ok(this.jobAdvertisementService.getByJobStatus());
    }

    @GetMapping("/getByDate")
    public ResponseEntity<?> getByDate() {
        return ResponseEntity.ok(this.jobAdvertisementService.getByDate());
    }

    @GetMapping("company/{companyName}")
    public ResponseEntity<?> getByCompany(@PathVariable String companyName) {
        return ResponseEntity.ok(this.jobAdvertisementService.getByCompanyName(companyName));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(this.jobAdvertisementService.getById(id));
    }

}
