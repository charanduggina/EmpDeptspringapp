package com.example.mysqldemo.service;

import com.example.mysqldemo.dao.DepartmentRepo;
import com.example.mysqldemo.dao.EmployeeRepo;
import com.example.mysqldemo.dto.EmployeeDTO;
import com.example.mysqldemo.exceptionHelper.EntityNotFoundException;
import com.example.mysqldemo.mapper.DepartmentMapper;
import com.example.mysqldemo.model.Department;
import com.example.mysqldemo.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Slf4j
@Service
public class EmployeeControllerService {
    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private DepartmentMapper departmentMapper;


    /*
    private final DepartmentRepo departmentRepo;
    private final EmployeeRepo employeeRepo;
    private final DepartmentMapper departmentMapper;

    @Autowired
    public  EmployeeControllerService(EmployeeRepo employeeRepo,DepartmentRepo departmentRepo,DepartmentMapper departmentMapper){
        this.departmentRepo = departmentRepo;
        this.employeeRepo = employeeRepo;
        this.departmentMapper = departmentMapper;
    }
    */


    public List<EmployeeDTO> getAllEmployees(){

        return employeeRepo.findAll().stream().map(departmentMapper::toEmployeeDto).collect(Collectors.toCollection(ArrayList::new));
    }
    public Optional<EmployeeDTO> getEmployeeById(int empId) throws InvalidInputException {
        log.info("hi entered");
        System.out.println("id "+empId);
        if(0 == empId ) {
            throw new InvalidInputException("Employee Id is not valid");
        }


        //Employee employee = employeeRepo.findById(empId).get();

        return Optional.of(departmentMapper.toEmployeeDto(employeeRepo.findById(empId).get()));
    }

    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO){
        Department department = departmentRepo.findById(employeeDTO.getDepartmentDTO().getDeptnumber()).orElseThrow(()-> new RuntimeException("No department found with the given id"));
        Employee employee = departmentMapper.toEmployeeModel(employeeDTO);
        employee.setDepartment(department);
        return departmentMapper.toEmployeeDto(employeeRepo.save(employee));
    }

    public void deleteEmployee(int empId) throws InvalidInputException {
        if(0 == empId ) {
            throw new InvalidInputException("Employee Id is not valid");
        }

        employeeRepo.deleteById(empId);
    }
    public EmployeeDTO editEmployee(EmployeeDTO employeeDTO){
        Department department = departmentRepo.findById(employeeDTO.getDepartmentDTO().getDeptnumber()).orElseThrow(()-> new RuntimeException("No department found with the given id"));
        Employee employee = departmentMapper.toEmployeeModel(employeeDTO);
        employee.setDepartment(department);
        return departmentMapper.toEmployeeDto(employeeRepo.save(employee));
    }

}
