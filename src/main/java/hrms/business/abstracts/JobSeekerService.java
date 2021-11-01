package hrms.business.abstracts;

import java.util.List;

import hrms.entities.concretes.JobSeeker;

public interface JobSeekerService {

    List<JobSeeker> getAll();

}
