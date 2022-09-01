package com.employee.backend.service.impl;

import com.employee.backend.model.Employee;
import com.employee.backend.repository.EmployeeRespository;
import com.employee.backend.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRespository employeeRespository;


    public EmployeeServiceImpl(EmployeeRespository employeeRespository) {
        super();
        this.employeeRespository = employeeRespository;
    }


    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRespository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRespository.findAll();
    }
}
