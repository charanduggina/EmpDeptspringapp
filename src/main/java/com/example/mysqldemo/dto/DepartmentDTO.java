package com.example.mysqldemo.dto;


import com.example.mysqldemo.model.Department;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Id;
import java.util.Objects;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO  {

    private int Deptnumber;
    private String Deptname;
    private String Location;

    public static DepartmentDTO modelToDto(Department department){
        DepartmentDTO departmentDTO = null;
        if(!Objects.isNull(department)){
            departmentDTO = new DepartmentDTO();
            departmentDTO.setDeptname(department.getDeptname());
            departmentDTO.setDeptnumber(department.getDeptnumber());
            departmentDTO.setLocation(department.getLocation());
        }
        return departmentDTO;
    }

}
