package hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hrms.business.abstracts.EmployerService;
import hrms.entities.concretes.Employer;

@RestController
@CrossOrigin
@RequestMapping("api/employers")
public class EmployersController {
    
    private EmployerService employerService;

    @Autowired
    public EmployersController(EmployerService employerService) {

        this.employerService = employerService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Employer employer){
        return ResponseEntity.ok(this.employerService.add(employer));  
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.employerService.getAll());
    }

}
