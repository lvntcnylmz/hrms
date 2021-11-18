package hrms.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hrms.business.abstracts.JobAdvertisementService;
import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.Result;
import hrms.entities.concretes.JobAdvertisement;
import hrms.entities.dtos.JobAdvertisementDto;


@RestController
@CrossOrigin
@RequestMapping("/api/jobAdvertisements")
public class JobAdvertisementsController {
    
    private JobAdvertisementService jobAdvertisementService;

    public JobAdvertisementsController(JobAdvertisementService jobAdvertisementService) {
        this.jobAdvertisementService = jobAdvertisementService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody JobAdvertisement jobAdvertisement){
        return this.jobAdvertisementService.add(jobAdvertisement);
    }

    @GetMapping("/getAll")
    public DataResult<List<JobAdvertisementDto>> getAllAdvertisement(){
        return this.jobAdvertisementService.getAllAdvertisement();
    }
    
    @GetMapping("/getByJobStatus")
    public DataResult<List<JobAdvertisementDto>> getByJobStatus(){
        return this.jobAdvertisementService.getByJobStatus();
    }

    @GetMapping("/getByDate")
    public DataResult<List<JobAdvertisementDto>> getByDate(){
        return this.jobAdvertisementService.getByDate();
    }

    @GetMapping("/getByCompany")
    public DataResult<List<JobAdvertisementDto>> getByCompany(@RequestParam String companyName){
        return this.jobAdvertisementService.getByCompanyName(companyName);
    }

    @GetMapping("/getByJobId")
    public DataResult<List<JobAdvertisementDto>> getByJobId(@RequestParam int jobId){
        return this.jobAdvertisementService.getJobById(jobId);
    }

}
