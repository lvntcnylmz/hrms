package hrms.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hrms.business.abstracts.JobAdvertisementService;
import hrms.entities.concretes.JobAdvertisement;


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

    @GetMapping("/getByCompany")
    public ResponseEntity<?> getByCompany(@RequestParam String companyName) {
        return ResponseEntity.ok(this.jobAdvertisementService.getByCompanyName(companyName));
    }

    @GetMapping("/getByJobId")
    public ResponseEntity<?> getByJobId(@RequestParam int jobId) {
        return ResponseEntity.ok(this.jobAdvertisementService.getJobById(jobId));
    }

}
