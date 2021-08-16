package com.example.mysqldemo.dto;

import com.example.mysqldemo.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Objects;
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO extends ResponseDTO {
    private int empid;
    private String firstname;
    private String lastname;
    private String joindate;
    private String phoneno;
   private DepartmentDTO departmentDTO;
   // private int deptNo;
    private String salary;




}
