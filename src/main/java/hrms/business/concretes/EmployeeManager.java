package hrms.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import hrms.business.abstracts.EmployeeService;
import hrms.dataAccess.EmployeeDao;
import hrms.entities.concretes.Employee;

@Service
public class EmployeeManager implements EmployeeService {

    private EmployeeDao employeeDao;

    public EmployeeManager(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> getAll() {
        return this.employeeDao.findAll();
    }
    
}
