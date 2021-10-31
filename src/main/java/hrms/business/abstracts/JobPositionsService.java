package hrms.business.abstracts;

import java.util.List;

import hrms.entities.concretes.JobPosition;

public interface JobPositionsService {
    
    List<JobPosition> getAll();

}
