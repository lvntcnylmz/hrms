package hrms.business.concretes;

import hrms.business.abstracts.SkillService;
import hrms.core.utils.messages.Message;
import hrms.core.utils.results.Result;
import hrms.core.utils.results.SuccessDataResult;
import hrms.dataAccess.abstracts.SkillDao;
import hrms.entities.concretes.Skill;
import org.springframework.stereotype.Service;

@Service
public class SkillManager implements SkillService {

    private final SkillDao skillDao;

    public SkillManager(SkillDao skillDao) {
        this.skillDao = skillDao;
    }

    @Override
    public Result add(Skill skill) {
        return new SuccessDataResult<>(this.skillDao.save(skill), Message.SAVED);
    }

}
