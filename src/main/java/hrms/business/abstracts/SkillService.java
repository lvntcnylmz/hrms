package hrms.business.abstracts;

import hrms.core.utils.results.Result;
import hrms.entities.concretes.Skill;

public interface SkillService {
    
    Result add(Skill skill);

}
