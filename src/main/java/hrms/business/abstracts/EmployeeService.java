package hrms.business.abstracts;

import java.util.List;

import hrms.core.utils.results.DataResult;
import hrms.entities.concretes.Employee;

public interface EmployeeService {

    DataResult<List<Employee>> getAll();

}
