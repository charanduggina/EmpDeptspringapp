package com.example.mysqldemo.service;

import com.example.mysqldemo.dao.DepartmentRepo;
import com.example.mysqldemo.dao.EmployeeRepo;
import com.example.mysqldemo.dto.DepartmentDTO;
import com.example.mysqldemo.mapper.DepartmentMapper;
import com.example.mysqldemo.model.Department;
import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentControllerService {
    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private DepartmentMapper departmentMapper;


    public List<DepartmentDTO> getAllDepartments(){

        return departmentRepo.findAll().stream().map(DepartmentDTO::modelToDto).collect(Collectors.toCollection(ArrayList::new));
    }

    public Optional<DepartmentDTO> getDepartmentById(int deptnumber) throws InvalidInputException {
        if(0 == deptnumber ) {
            throw new InvalidInputException("Department Id is not valid");
        }


        return Optional.of(DepartmentDTO.modelToDto(departmentRepo.findById(deptnumber).get()));
    }
    public DepartmentDTO saveDepartment(Department department){
        Department saveddepartment = departmentRepo.save(department);
       return  DepartmentDTO.modelToDto(saveddepartment);
    }

    public void deleteDepartment(int deptId) throws InvalidInputException {
        if(0 == deptId ) {
            throw new InvalidInputException("Department Id is not valid");
        }

        departmentRepo.deleteById(deptId);
    }
}
