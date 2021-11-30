package hrms.business.concretes;

import org.springframework.stereotype.Service;

import hrms.business.abstracts.SkillService;
import hrms.core.utils.results.Result;
import hrms.core.utils.results.SuccessDataResult;
import hrms.dataAccess.abstracts.SkillDao;
import hrms.entities.concretes.Skill;

@Service
public class SkillManager implements SkillService {

    private SkillDao skillDao;

    public SkillManager(SkillDao skillDao) {
        this.skillDao = skillDao;
    }

    @Override
    public Result add(Skill skill) {
        return new SuccessDataResult<Skill>(this.skillDao.save(skill), "Skill was saved.");
    }
    
}
