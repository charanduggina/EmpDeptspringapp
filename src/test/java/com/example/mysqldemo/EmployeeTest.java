package com.example.mysqldemo;

import com.example.mysqldemo.Constants.Message;
import com.example.mysqldemo.controller.DepartmentController;
import com.example.mysqldemo.controller.EmployeeController;
import com.example.mysqldemo.dao.DepartmentRepo;
import com.example.mysqldemo.dao.EmployeeRepo;
import com.example.mysqldemo.dto.ResponseDTO;
import com.example.mysqldemo.model.Department;
import com.example.mysqldemo.model.Employee;
import com.example.mysqldemo.repository.EmployeeRepository;
import com.example.mysqldemo.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
@Slf4j
@SpringBootTest
public class EmployeeTest {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void testmsgSuccess() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MvcResult resultobj = mockMvc.perform(MockMvcRequestBuilders.get("/api/test/true"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        ResponseDTO responseDTO = objectMapper.readValue(resultobj.getResponse().getContentAsString(),ResponseDTO.class);
        Assertions.assertNotNull(responseDTO);
        Assertions.assertEquals(responseDTO.getMsg(), Message.EMP_LOGIC_MSG_SUCCESS);
        Assertions.assertEquals(21,responseDTO.getEmpcount());



    }


    @Test
    public void testmsgFiled() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MvcResult resultobj = mockMvc.perform(MockMvcRequestBuilders.get("/api/test/false"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

        ResponseDTO responseDTO = objectMapper.readValue(resultobj.getResponse().getContentAsString(),ResponseDTO.class);
        Assertions.assertNotNull(responseDTO);
        Assertions.assertEquals(responseDTO.getMsg(), Message.EMP_LOGIC_MSG_FAILED);




    }


    @InjectMocks
    EmployeeController employeeController;
    @Mock
    EmployeeRepo employeeRepo;
    @BeforeEach
    void setUp()throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    /*
    @Test
    public void testGetEmployeebyId() throws Exception{
        Employee employee = new Employee();
        employee.setEmpid(5);
       // employee.setDeptno(5);
        employee.setFirstname("charan");
        employee.setLastname("duggina");
        employee.setJoindate("20/08/2020");
        employee.setPhoneno("9618984265");
        employee.setSalary("40000");

        when(employeeRepo.findById(5)).thenReturn(Optional.of(employee));

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MvcResult resultobj1 = mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/5"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Employee employee1 = objectMapper.readValue(resultobj1.getResponse().getContentAsString(),Employee.class);
       //System.out.println("hi testing id"+ employee1.getEmpid());


        Optional<Employee> employeeOptional = Optional.of(employee);
        log.info("hi testing");
        Optional<Employee> employee1 = employeeController.getEmployee(5);
        Assertions.assertNotNull(employee1);
        Assertions.assertEquals(employee1.map(Employee ::getEmpid), employeeOptional.map(Employee::getEmpid));
       // Assertions.assertEquals(employee1.map(Employee ::getDeptno), employeeOptional.map(Employee::getDeptno));
       Assertions.assertEquals(employee1.map(Employee ::getFirstname), employeeOptional.map(Employee::getFirstname));

    }
    */

    @InjectMocks
    DepartmentController departmentController;
    @Mock
    DepartmentRepo departmentRepo;

/*
    @Test
    public void testGetDepartmentbyId() throws Exception{
        Department department = new Department();
        department.setDeptnumber(1);
        department.setDeptname("technology");
        department.setLocation("hyderabad");

        when(departmentRepo.findById(1)).thenReturn(Optional.of(department));

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MvcResult resultobj2 = mockMvc.perform(MockMvcRequestBuilders.get("/api/department/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Department department1 = objectMapper.readValue(resultobj1.getResponse().getContentAsString(),Department.class);



        Optional<Department> departmentOptional = Optional.of(department);
        log.info("hi testing dept");
        Optional<Department> department1 = departmentController.getDepartment(1);
        Assertions.assertNotNull(department1);
        Assertions.assertEquals(department1.map(Department ::getDeptnumber), departmentOptional.map(Department ::getDeptnumber));
        Assertions.assertEquals(department1.map(Department ::getDeptname), departmentOptional.map(Department ::getDeptname));
        Assertions.assertEquals(department1.map(Department ::getLocation), departmentOptional.map(Department ::getLocation));

    }
    */
}
