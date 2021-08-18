package com.example.mysqldemo.service;

import com.example.mysqldemo.dao.DepartmentRepo;
import com.example.mysqldemo.dao.EmployeeRepo;
import com.example.mysqldemo.dto.EmployeeDTO;
import com.example.mysqldemo.mapper.DepartmentMapper;
import com.example.mysqldemo.model.Department;
import com.example.mysqldemo.model.Employee;
import com.example.mysqldemo.repository.EmployeeRepository;
import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeControllerService {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private DepartmentMapper departmentMapper;

    public List<Employee> getAllEmployees(){
        return employeeRepo.findAll();
    }
    public Optional<Employee> getEmployeeById(int empId) throws InvalidInputException {
        if(0 == empId ) {
            throw new InvalidInputException("Employee Id is not valid");
        }

        Employee employee = employeeRepo.findById(empId).get();

        return Optional.of(employeeRepo.findById(empId).get());
    }

    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO){
        Department department = departmentRepo.findById(employeeDTO.getDepartmentDTO().getDeptnumber()).orElseThrow(()-> new RuntimeException("No department found with the given id"));
        Employee employee = departmentMapper.toModel(employeeDTO);
        employee.setDepartment(department);
        return departmentMapper.toDto(employeeRepo.save(employee));
    }

    public void deleteEmployee(int empId) throws InvalidInputException {
        if(0 == empId ) {
            throw new InvalidInputException("Employee Id is not valid");
        }

        employeeRepo.deleteById(empId);
    }
    public EmployeeDTO editEmployee(EmployeeDTO employeeDTO){
        Department department = departmentRepo.findById(employeeDTO.getDepartmentDTO().getDeptnumber()).orElseThrow(()-> new RuntimeException("No department found with the given id"));
        Employee employee = departmentMapper.toModel(employeeDTO);
        employee.setDepartment(department);
        return departmentMapper.toDto(employeeRepo.save(employee));
    }

}
