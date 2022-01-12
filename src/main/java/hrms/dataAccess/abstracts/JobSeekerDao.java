package hrms.dataAccess.abstracts;

import hrms.entities.concretes.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobSeekerDao extends JpaRepository<JobSeeker, Integer> {

    boolean existsJobSeekerByNationalId(String nationalId);

    boolean existsJobSeekerByEmail(String email);

}
