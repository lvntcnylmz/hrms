package hrms.api.controllers;

import hrms.business.abstracts.JobAdvertisementService;
import hrms.entities.concretes.JobAdvertisement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/api/jobAdvertisements")
public class JobAdvertisementsController {

    private JobAdvertisementService jobAdvertisementService;

    public JobAdvertisementsController(JobAdvertisementService jobAdvertisementService) {
        this.jobAdvertisementService = jobAdvertisementService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody JobAdvertisement jobAdvertisement) {
        return ResponseEntity.ok(this.jobAdvertisementService.add(jobAdvertisement));
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllAdvertisement() {
        return ResponseEntity.ok(this.jobAdvertisementService.getAllAdvertisement());
    }

    @GetMapping("/getByJobStatus")
    public ResponseEntity<?> getByJobStatus() {
        return ResponseEntity.ok(this.jobAdvertisementService.getByJobStatus());
    }

    @GetMapping("/getByDate")
    public ResponseEntity<?> getByDate() {
        return ResponseEntity.ok(this.jobAdvertisementService.getByDate());
    }

    @GetMapping("/getByCompany/{companyName}")
    public ResponseEntity<?> getByCompany(@PathVariable String companyName) {
        return ResponseEntity.ok(this.jobAdvertisementService.getByCompanyName(companyName));
    }

    @GetMapping("/getJobById/{id}")
    public ResponseEntity<?> getByJobId(@PathVariable Integer id) {
        return ResponseEntity.ok(this.jobAdvertisementService.getJobById(id));
    }

}
