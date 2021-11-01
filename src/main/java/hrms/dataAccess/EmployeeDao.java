package hrms.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import hrms.entities.concretes.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Integer> {
    
}
