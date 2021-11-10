package hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hrms.business.abstracts.LanguageService;
import hrms.core.utils.results.Result;
import hrms.entities.concretes.Language;

@RestController
@CrossOrigin
@RequestMapping("api/languages")
public class LanguagesController {
    
    private LanguageService languageService;

    @Autowired
    public LanguagesController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody Language language){
        return this.languageService.add(language);
    }

}
