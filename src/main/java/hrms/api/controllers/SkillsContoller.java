package hrms.api.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hrms.business.abstracts.SkillService;
import hrms.core.utils.results.Result;
import hrms.entities.concretes.Skill;

@RestController
@CrossOrigin
@RequestMapping("/api/skills")
public class SkillsContoller {
    
    private final SkillService skillService;

    public SkillsContoller(SkillService skillService) {
        this.skillService = skillService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody Skill skill){
        return this.skillService.add(skill);
    }

}
