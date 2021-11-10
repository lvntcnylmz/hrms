package hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hrms.business.abstracts.SchoolService;
import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.Result;
import hrms.entities.concretes.School;

@RestController
@CrossOrigin
@RequestMapping("/api/schools")
public class SchoolsController {
    
    private SchoolService schoolService;

    @Autowired
    public SchoolsController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody School school){
        return this.schoolService.add(school);
    }

    @GetMapping("/getAll")
    public DataResult<List<School>> getAll(){
        return this.schoolService.getAll();
    }

}
