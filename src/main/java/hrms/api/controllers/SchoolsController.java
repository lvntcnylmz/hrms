package hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hrms.business.abstracts.SchoolService;
import hrms.entities.concretes.School;

@RestController
@CrossOrigin
@RequestMapping("/api/schools")
public class SchoolsController {
    
    private final SchoolService schoolService;

    @Autowired
    public SchoolsController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody School school){
        return ResponseEntity.ok(this.schoolService.add(school)); 
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.schoolService.getAll()); 
    }

}
