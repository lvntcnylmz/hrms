package hrms.business.concretes;

import hrms.business.abstracts.LanguageService;
import hrms.core.utils.messages.Message;
import hrms.core.utils.results.Result;
import hrms.core.utils.results.SuccessDataResult;
import hrms.dataAccess.abstracts.LanguageDao;
import hrms.entities.concretes.Language;
import org.springframework.stereotype.Service;

@Service
public class LanguageManager implements LanguageService {

    private final LanguageDao languageDao;

    public LanguageManager(LanguageDao languageDao) {
        this.languageDao = languageDao;
    }

    @Override
    public Result add(Language language) {
        return new SuccessDataResult<Language>(this.languageDao.save(language), Message.SAVED);
    }

}
