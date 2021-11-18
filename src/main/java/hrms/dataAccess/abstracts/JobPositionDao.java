package hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hrms.entities.concretes.JobPosition;

public interface JobPositionDao extends JpaRepository<JobPosition, Integer> {

    boolean existsByTitleIgnoreCase(String jobTitle);

    List<JobPosition> findById(int jobId);

}