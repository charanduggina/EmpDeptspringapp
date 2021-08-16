package com.example.mysqldemo.mapper;

import com.example.mysqldemo.dto.DepartmentDTO;
import com.example.mysqldemo.dto.EmployeeDTO;
import com.example.mysqldemo.model.Department;
import com.example.mysqldemo.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

    //@Mapping(source = "department.Location",target = "place")//defaultValue = "abc" this is to set the default value
     DepartmentDTO modelTODto(Department department);
     EmployeeDTO toDto(Employee employee);
     Department toModel(DepartmentDTO departmentDTO);
     Employee toModel(EmployeeDTO employeeDTO);


}
