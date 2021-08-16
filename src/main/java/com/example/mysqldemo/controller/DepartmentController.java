package com.example.mysqldemo.controller;

import com.example.mysqldemo.dao.DepartmentRepo;
import com.example.mysqldemo.dao.EmployeeRepo;
import com.example.mysqldemo.dto.DepartmentDTO;
import com.example.mysqldemo.dto.EmployeeDTO;
import com.example.mysqldemo.mapper.DepartmentMapper;
import com.example.mysqldemo.model.Department;
import com.example.mysqldemo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")


public class DepartmentController {
    @Autowired
    DepartmentMapper deptmap;
    @Autowired
    DepartmentRepo repo;
    @GetMapping("/departments")
    public List<Department> getDepartment(){

       return repo.findAll();

    }

    @RequestMapping("/department/{Deptnumber}")
    public Optional<Department> getDepartment(@PathVariable("Deptnumber") int Deptnumber){
        return repo.findById(Deptnumber);
    }

    @PostMapping("/department")
    public ResponseEntity<DepartmentDTO> addDepartment(Department department){
        Department saveddepartment = repo.save(department);

        return new ResponseEntity( deptmap.modelTODto(saveddepartment), HttpStatus.CREATED);//.ok( EmployeeDTO.fromEmployee(savedemployee));

    }
    @DeleteMapping("/department/{Deptnumber}")
    public String deleteDepartment(@PathVariable("Deptnumber") int Deptnumber){
        Department department = repo.getOne(Deptnumber);
        repo.delete(department);
        return "deleted";
    }


}
