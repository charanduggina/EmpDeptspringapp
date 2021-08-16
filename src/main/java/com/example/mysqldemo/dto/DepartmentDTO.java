package com.example.mysqldemo.dto;


import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Id;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO  {

    private int Deptnumber;
    private String Deptname;
    private String Location;
}
