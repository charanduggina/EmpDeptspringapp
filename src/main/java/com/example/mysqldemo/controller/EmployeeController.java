package com.example.mysqldemo.controller;

import com.example.mysqldemo.dao.DepartmentRepo;
import com.example.mysqldemo.dao.EmployeeRepo;
import com.example.mysqldemo.dto.EmployeeDTO;
import com.example.mysqldemo.dto.ResponseDTO;
import com.example.mysqldemo.mapper.DepartmentMapper;
import com.example.mysqldemo.model.Department;
import com.example.mysqldemo.model.Employee;
import com.example.mysqldemo.service.EmployeeControllerService;
import com.example.mysqldemo.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Slf4j

@RestController
@RequestMapping("/api")

public class EmployeeController {

    // here we are not creating any class or oject for the interface
    @Autowired
    private EmployeeControllerService empservice;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private DepartmentMapper departmentMapper;


    /*
    @RequestMapping("/addemployee")
    public String addemployee(Employee employee){
        repo.save(employee);
        return "home.jsp";
    }
    @RequestMapping("/getemployee")
    public ModelAndView getemployeeold(@RequestParam int empid){ 
        ModelAndView mv = new ModelAndView("showemployee.jsp");
        Employee employee = repo.findById(empid).orElse(new Employee());
        System.out.println("hi"+repo.findByidgraeter1());
        mv.addObject(employee);
        return mv;
    }

     */
    @GetMapping("/employees")
    @ApiOperation(
            value= "get all the Employee list",
            notes = "this api gives the list of all the employee saved in the database"
    )
    public ResponseEntity<List<EmployeeDTO>> getEmployees(){
        return new ResponseEntity( empservice.getAllEmployees(), HttpStatus.OK);
    }
    @RequestMapping("/employee/{empid}")
    public ResponseEntity<Optional<EmployeeDTO>> getEmployee(@ApiParam(value = "ID of the employee you want to retrive",required = true) @PathVariable("empid") int empid) throws InvalidInputException {
        log.trace("A TRACE Message");
        log.debug("A DEBUG Message");
        log.info("An INFO Message");
        log.warn("A WARN Message");
        log.error("An ERROR Message");

        return new ResponseEntity(empservice.getEmployeeById(empid),HttpStatus.OK);
    }

    @PostMapping("/employee")
    @ApiOperation(
            value= "post the employee to db",
            notes = "the id fifield in the post api is auto incremented hence do not send the id in the post api"
    )
    public ResponseEntity<EmployeeDTO> restAddEmployee(@RequestBody EmployeeDTO employeeDTO){
        //System.out.println("hi testing 1"+employeeDTO);


        return new ResponseEntity( empservice.addEmployee(employeeDTO),HttpStatus.ACCEPTED);//.ok( EmployeeDTO.fromEmployee(savedemployee));

    }
    @DeleteMapping("/employee/{empid}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int empid) throws InvalidInputException {
       empservice.deleteEmployee(empid);
        return new ResponseEntity<>("Employee with ID :" + empid + " deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/employee")
    public ResponseEntity<EmployeeDTO> editEmployee(@RequestBody EmployeeDTO employeeDTO){
        return new ResponseEntity( empservice.editEmployee(employeeDTO),HttpStatus.ACCEPTED);
    }





    @GetMapping("/hello")
    ResponseEntity<String> hello() {
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }

    @GetMapping("/test/{isSuccess}")
    ResponseEntity<ResponseDTO> test(@PathVariable("isSuccess") Boolean isSuccess) {
        ResponseDTO responseDTO = employeeService.testlogic(isSuccess);
        return Boolean.TRUE.equals(isSuccess) ? ResponseEntity.ok(responseDTO) :
                ResponseEntity.badRequest().body(responseDTO);
    }

}

