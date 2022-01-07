package hrms.business.concretes;

import hrms.business.abstracts.SchoolService;
import hrms.core.utils.messages.Message;
import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.Result;
import hrms.core.utils.results.SuccessDataResult;
import hrms.dataAccess.abstracts.SchoolDao;
import hrms.entities.concretes.School;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolManager implements SchoolService {

    private final SchoolDao schoolDao;

    public SchoolManager(SchoolDao schoolDao) {
        this.schoolDao = schoolDao;
    }

    @Override
    public Result add(School school) {
        return new SuccessDataResult<School>(this.schoolDao.save(school), Message.SAVED);
    }

    @Override
    public DataResult<List<School>> getAll() {
        return new SuccessDataResult<List<School>>(this.schoolDao.findAll(), Message.LISTED);
    }

}
