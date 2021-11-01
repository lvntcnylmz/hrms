package hrms.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hrms.business.abstracts.EmployerService;
import hrms.entities.concretes.Employer;

@RestController
@CrossOrigin
@RequestMapping("api/employers")
public class EmployersController {
    
    private EmployerService employerService;

    public EmployersController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @GetMapping("/getAll")
    public List<Employer> getAll(){
        return this.employerService.getAll();
    }

}
