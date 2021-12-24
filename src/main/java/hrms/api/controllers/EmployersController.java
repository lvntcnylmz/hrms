package hrms.api.controllers;

import hrms.business.abstracts.EmployerService;
import hrms.entities.concretes.Employer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("api/employers")
public class EmployersController {

    private final EmployerService employerService;

    public EmployersController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody Employer employer) {
        return ResponseEntity.ok(this.employerService.add(employer));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(this.employerService.delete(id));
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
