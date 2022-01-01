package hrms.business.concretes;

import hrms.business.abstracts.ResumeService;
import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.Result;
import hrms.core.utils.results.SuccessDataResult;
import hrms.dataAccess.abstracts.ResumeDao;
import hrms.entities.concretes.Resume;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeManager implements ResumeService {

    private final ResumeDao resumeDao;

    public ResumeManager(ResumeDao resumeDao) {
        this.resumeDao = resumeDao;
    }

    @Override
    public Result add(Resume resume) {
        return new SuccessDataResult<Resume>(this.resumeDao.save(resume), "Resume was saved.");
    }

    @Override
    public DataResult<List<Resume>> getAll() {
        return new SuccessDataResult<List<Resume>>(this.resumeDao.findAll(), "Resumes are listed.");
    }

}
