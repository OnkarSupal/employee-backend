package com.employee.backend.service.impl;

import com.employee.backend.exception.ResourceNotFoundException;
import com.employee.backend.model.Employee;
import com.employee.backend.repository.EmployeeRespository;
import com.employee.backend.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Employee getEmployeeById(long id) {
    /*    Optional<Employee> employee = employeeRespository.findById(id);
        if(employee.isPresent())
            return employee.get();
        else
            throw new  ResourceNotFoundException("Employee","id",id);
        */
        return employeeRespository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee","id",id));
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {
        //Check employee present in DB
        Employee exitingEmployee = employeeRespository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee","id",id)
        );

        exitingEmployee.setFirstName(employee.getFirstName());
        exitingEmployee.setLastName(employee.getLastName());
        exitingEmployee.setEmail(employee.getEmail());

        employeeRespository.save(exitingEmployee);

        return exitingEmployee;
    }

    @Override
    public void deleteEmployee(long id) {
        employeeRespository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee","id",id)
        );

        employeeRespository.deleteById(id);
    }
}
