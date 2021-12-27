package hrms.dataAccess.abstracts;

import hrms.entities.concretes.JobAdvertisement;
import hrms.entities.dtos.JobAdvertisementDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement, Integer> {

    @Query("Select new hrms.entities.dtos.JobAdvertisementDto(ja.id, p.id, e.companyName, p.title, ja.status, ja.applicationDeadline, ja.numberOfOpenPosition, c.cityName, ja.description, ja.minSalary, ja.maxSalary)" +
            "From JobAdvertisement ja Inner Join ja.employer e Inner Join ja.jobPosition p Inner Join ja.city c Where status=true")
    List<JobAdvertisementDto> findByStatus();

    @Query("Select new hrms.entities.dtos.JobAdvertisementDto(ja.id, p.id, e.companyName, p.title, ja.status, ja.applicationDeadline, ja.numberOfOpenPosition, c.cityName, ja.description, ja.minSalary, ja.maxSalary)" +
            "From  JobAdvertisement ja Inner Join ja.employer e Inner Join ja.jobPosition p Inner Join ja.city c Order By ja.applicationDeadline ASC")
    List<JobAdvertisementDto> findByDate();

    @Query("Select new hrms.entities.dtos.JobAdvertisementDto(ja.id, p.id, e.companyName, p.title, ja.status, ja.applicationDeadline, ja.numberOfOpenPosition, c.cityName, ja.description, ja.minSalary, ja.maxSalary)" +
            "From  JobAdvertisement ja Inner Join ja.employer e Inner Join ja.jobPosition p Inner Join ja.city c Where Lower(e.companyName) like lower(concat('%', ?1,'%'))")
    List<JobAdvertisementDto> findByCompany(String companyName);

    @Query("Select new hrms.entities.dtos.JobAdvertisementDto(ja.id, p.id, e.companyName, p.title, ja.status, ja.applicationDeadline, ja.numberOfOpenPosition, c.cityName, ja.description, ja.minSalary, ja.maxSalary)" +
            "From  JobAdvertisement ja Inner Join ja.employer e Inner Join ja.jobPosition p Inner Join ja.city c")
    List<JobAdvertisementDto> findAllAdvertisement();

    @Query("Select new hrms.entities.dtos.JobAdvertisementDto(ja.id, p.id, e.companyName, p.title, ja.status, ja.applicationDeadline, ja.numberOfOpenPosition, c.cityName, ja.description, ja.minSalary, ja.maxSalary)" +
            "From  JobAdvertisement ja Inner Join ja.employer e Inner Join ja.jobPosition p Inner Join ja.city c Where p.id=:id")
    Optional<JobAdvertisementDto> findJobById(Integer id);

}
