package hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.business.abstracts.LanguageService;
import hrms.core.utils.results.Result;
import hrms.core.utils.results.SuccessDataResult;
import hrms.dataAccess.abstracts.LanguageDao;
import hrms.entities.concretes.Language;

@Service
public class LanguageManger implements LanguageService {

    private LanguageDao languageDao;

    @Autowired
    public LanguageManger(LanguageDao languageDao) {
        this.languageDao = languageDao;
    }

    @Override
    public Result add(Language language) {
        return new SuccessDataResult<Language>(this.languageDao.save(language), "Saved");
    }
    
}
