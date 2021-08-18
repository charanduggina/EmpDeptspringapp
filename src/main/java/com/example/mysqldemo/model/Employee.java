package com.example.mysqldemo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@ApiModel(description = "details about the employee table")
//@Table(name = "springsql1")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "the unique id of the employee")
    private int empid;
    @ApiModelProperty(notes = "the first name of the employee")
    private String firstname;
    @ApiModelProperty(notes = "the last name of the employee")
    private String lastname;
    @ApiModelProperty(notes = "the join date of the employee in the company")
    private String joindate;
    private String phoneno;

    private String salary;


    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "Deptnumber"), name = "Deptnumber")
    private Department department;



}
