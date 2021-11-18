package hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hrms.entities.concretes.JobAdvertisement;
import hrms.entities.dtos.JobAdvertisementDto;

public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement, Integer> {

    @Query("Select new hrms.entities.dtos.JobAdvertisementDto(ja.id, p.id, e.companyName, p.title, ja.status, ja.applicationDeadline, ja.numberOfOpenPosition)" +
        "From JobAdvertisement ja Inner Join ja.employer e Inner Join ja.jobPosition p Where status=true")
    List<JobAdvertisementDto> findByStatus();

    @Query("Select new hrms.entities.dtos.JobAdvertisementDto(ja.id, p.id, e.companyName, p.title, ja.status, ja.applicationDeadline, ja.numberOfOpenPosition)" + 
        "From  JobAdvertisement ja Inner Join ja.employer e Inner Join ja.jobPosition p Order By ja.applicationDeadline ASC")
    List<JobAdvertisementDto> findByDate();

    @Query("Select new hrms.entities.dtos.JobAdvertisementDto(ja.id, p.id, e.companyName, p.title, ja.status, ja.applicationDeadline, ja.numberOfOpenPosition)" + 
        "From  JobAdvertisement ja Inner Join ja.employer e Inner Join ja.jobPosition p Where Lower(e.companyName) like lower(concat('%', ?1,'%'))")
    List<JobAdvertisementDto> findByCompany(String companyName);

    @Query("Select new hrms.entities.dtos.JobAdvertisementDto(ja.id, p.id, e.companyName, p.title, ja.status, ja.applicationDeadline, ja.numberOfOpenPosition)" + 
    "From  JobAdvertisement ja Inner Join ja.employer e Inner Join ja.jobPosition p")
    List<JobAdvertisementDto> findAllAdvertisement();

    @Query("Select new hrms.entities.dtos.JobAdvertisementDto(ja.id, p.id, e.companyName, p.title, ja.status, ja.applicationDeadline, ja.numberOfOpenPosition)" + 
        "From  JobAdvertisement ja Inner Join ja.employer e Inner Join ja.jobPosition p Where p.id=:jobId")
    List<JobAdvertisementDto> findJobById(int jobId);

}
