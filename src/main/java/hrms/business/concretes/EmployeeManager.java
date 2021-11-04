package hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.business.abstracts.EmployeeService;
import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.SuccessDataResult;
import hrms.dataAccess.abstracts.EmployeeDao;
import hrms.entities.concretes.Employee;

@Service
public class EmployeeManager implements EmployeeService {

    private EmployeeDao employeeDao;

    @Autowired
    public EmployeeManager(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public DataResult<List<Employee>> getAll() {
        return new SuccessDataResult<List<Employee>>(this.employeeDao.findAll(), "Employees are listed.");
    }
    
}
