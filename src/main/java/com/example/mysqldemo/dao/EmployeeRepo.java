package com.example.mysqldemo.dao;

import com.example.mysqldemo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee,Integer> {
    List<Employee> findByfirstname(String name);

    List<Employee> findByempidGreaterThan(int empid);

    @Query(value="select * from Employee where empid >= 1",nativeQuery=true)
    List<Employee> findByidgraeter1();

}
