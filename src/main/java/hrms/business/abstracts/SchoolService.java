package hrms.business.abstracts;

import java.util.List;

import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.Result;
import hrms.entities.concretes.School;

public interface SchoolService {
    
    Result add(School school);

    DataResult<List<School>> getAll();

}
