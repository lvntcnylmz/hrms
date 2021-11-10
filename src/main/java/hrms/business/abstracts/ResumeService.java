package hrms.business.abstracts;

import java.util.List;

import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.Result;
import hrms.entities.concretes.Resume;

public interface ResumeService {
    
    Result add(Resume resume);

    DataResult<List<Resume>> getAll();

}
