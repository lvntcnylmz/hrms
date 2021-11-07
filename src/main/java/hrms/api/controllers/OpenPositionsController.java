package hrms.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hrms.business.abstracts.JobAdvertisementService;
import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.Result;
import hrms.entities.concretes.JobAdvertisement;


@RestController
@CrossOrigin
@RequestMapping("/api/openPositions")
public class OpenPositionsController {
    
    private JobAdvertisementService jobAdvertisementService;

    public OpenPositionsController(JobAdvertisementService jobAdvertisementService) {
        this.jobAdvertisementService = jobAdvertisementService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody JobAdvertisement jobAdvertisement){
        return this.jobAdvertisementService.add(jobAdvertisement);
    }

    @GetMapping("/getAll")
    public DataResult<List<JobAdvertisement>> getAll(){
        return this.jobAdvertisementService.getAll();
    }

}
