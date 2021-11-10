package hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import hrms.entities.concretes.Photo;

public interface PhotoDao extends JpaRepository<Photo, Integer> {
    
}
