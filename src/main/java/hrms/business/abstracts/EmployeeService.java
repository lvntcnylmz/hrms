package hrms.business.abstracts;

import java.util.List;

import hrms.entities.concretes.Employee;

public interface EmployeeService {

    List<Employee> getAll();

}
