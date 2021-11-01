package hrms.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import hrms.entities.concretes.JobSeeker;

public interface JobSeekerDao extends JpaRepository<JobSeeker, Integer> {
    
}
