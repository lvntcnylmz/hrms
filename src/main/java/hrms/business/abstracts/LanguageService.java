package hrms.business.abstracts;

import hrms.core.utils.results.Result;
import hrms.entities.concretes.Language;

public interface LanguageService {
    
    Result add(Language language);

}
