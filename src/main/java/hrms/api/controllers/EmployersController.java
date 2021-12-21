package hrms.api.controllers;

import hrms.business.abstracts.EmployerService;
import hrms.entities.concretes.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> add(@RequestBody Employer employer) {
        return ResponseEntity.ok(this.employerService.add(employer));
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(this.employerService.getAll());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return ResponseEntity.ok(this.employerService.getById(id));
    }

}
