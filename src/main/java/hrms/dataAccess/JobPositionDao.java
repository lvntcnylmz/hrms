package hrms.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import hrms.entities.concretes.JobPosition;

public interface JobPositionDao extends JpaRepository<JobPosition, Integer> {

}