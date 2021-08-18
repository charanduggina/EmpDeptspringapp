package com.example.mysqldemo.controller;

import com.example.mysqldemo.dao.DepartmentRepo;
import com.example.mysqldemo.dto.DepartmentDTO;
import com.example.mysqldemo.mapper.DepartmentMapper;
import com.example.mysqldemo.model.Department;
import com.example.mysqldemo.model.Employee;
import com.example.mysqldemo.service.DepartmentControllerService;
import org.eclipse.jdt.core.compiler.InvalidInputException;
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
    private DepartmentMapper deptmap;
    @Autowired
    private DepartmentRepo repo;
    @Autowired
    private DepartmentControllerService deptService;
    @GetMapping("/departments")
    public ResponseEntity<List<Employee>> getDepartment(){

        return new ResponseEntity( deptService.getAllDepartments(), HttpStatus.OK);

    }

    @RequestMapping("/department/{Deptnumber}")
    public ResponseEntity<Optional<Department>> getDepartment(@PathVariable("Deptnumber") int deptnumber) throws InvalidInputException {
        return new ResponseEntity(deptService.getDepartmentById(deptnumber),HttpStatus.OK);
    }

    @PostMapping("/department")
    public ResponseEntity<DepartmentDTO> addDepartment(Department department){


        return new ResponseEntity( deptService.saveDepartment(department), HttpStatus.CREATED);//.ok( EmployeeDTO.fromEmployee(savedemployee));

    }
    @DeleteMapping("/department/{Deptnumber}")
    public  ResponseEntity<String> deleteDepartment(@PathVariable("Deptnumber") int Deptnumber) throws InvalidInputException {
        deptService.deleteDepartment(Deptnumber);
        return new ResponseEntity<>("Department with ID :" + Deptnumber + " deleted successfully", HttpStatus.OK);
    }


}
