package hrms.dataAccess.abstracts;

import hrms.entities.dtos.request.JobSeekerRegisterDto;
import org.springframework.data.jpa.repository.JpaRepository;

import hrms.entities.concretes.JobSeeker;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface JobSeekerDao extends JpaRepository<JobSeeker, Integer> {

    boolean existsJobSeekerByNationalId(String nationalId);

    boolean existsJobSeekerByEmail(String email);

    @Query("Select new hrms.entities.dtos.request.JobSeekerRegisterDto(js.firstName, js.lastName, js.dateOfBirth, js.nationalId, u.email, u.password)" +
            "From JobSeeker js Inner Join User u")
    Optional<JobSeekerRegisterDto> findJobSeekerById(Integer id);
}
