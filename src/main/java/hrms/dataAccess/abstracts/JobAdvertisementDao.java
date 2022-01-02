package hrms.dataAccess.abstracts;

import hrms.entities.concretes.JobAdvertisement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement, Integer> {

    List<JobAdvertisement> findByStatusTrue();

    List<JobAdvertisement> findByOrderByApplicationDeadlineAsc();

    List<JobAdvertisement> findByEmployer(String companyName);

}
