package com.example.mysqldemo.dao;

import com.example.mysqldemo.model.Department;
import com.example.mysqldemo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;



public interface DepartmentRepo extends JpaRepository<Department,Integer> {

}