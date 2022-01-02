package hrms.api.controllers;

import hrms.business.abstracts.EmployerService;
import hrms.entities.concretes.Employer;
import hrms.entities.dtos.request.EmployerRegisterDto;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("api/employers")
public class EmployersController {

    private final EmployerService employerService;
    private final ModelMapper modelMapper;

    public EmployersController(EmployerService employerService, ModelMapper modelMapper) {
        this.employerService = employerService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody EmployerRegisterDto employer) {
        Employer employerRegister = this.modelMapper.map(employer, Employer.class);
        return ResponseEntity.ok(this.employerService.add(employerRegister));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(this.employerService.delete(id));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(this.employerService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(this.employerService.getById(id));
    }

}
