package hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import hrms.entities.concretes.Resume;

public interface ResumeDao extends JpaRepository<Resume, Integer> {
    
}
