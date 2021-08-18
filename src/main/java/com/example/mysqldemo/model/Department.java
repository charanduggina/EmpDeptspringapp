package com.example.mysqldemo.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
 // why to use table here
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Deptnumber;
    private String Deptname;
    private String Location;
    @JsonBackReference
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employee;



}
