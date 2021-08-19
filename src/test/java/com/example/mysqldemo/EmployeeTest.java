package com.example.mysqldemo;

import com.example.mysqldemo.Constants.Message;
import com.example.mysqldemo.controller.DepartmentController;
import com.example.mysqldemo.controller.EmployeeController;
import com.example.mysqldemo.dao.DepartmentRepo;
import com.example.mysqldemo.dao.EmployeeRepo;
import com.example.mysqldemo.dto.DepartmentDTO;
import com.example.mysqldemo.dto.EmployeeDTO;
import com.example.mysqldemo.dto.ResponseDTO;
import com.example.mysqldemo.mapper.DepartmentMapper;
import com.example.mysqldemo.model.Department;
import com.example.mysqldemo.model.Employee;
import com.example.mysqldemo.repository.EmployeeRepository;
import com.example.mysqldemo.service.DepartmentControllerService;
import com.example.mysqldemo.service.EmployeeControllerService;
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


    @Autowired
    private DepartmentMapper departmentMapper;
    @InjectMocks
    EmployeeControllerService employeeControllerService;
    @Mock
    EmployeeRepo employeeRepo;
    @BeforeEach
    void setUp()throws Exception {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testGetEmployeebyId() throws Exception{
        Employee employee = new Employee();
        employee.setEmpid(3);
        Department department = new Department();
        department.setDeptnumber(1);
        employee.setDepartment(department);
        employee.setFirstname("charan");
        employee.setLastname("duggina");
        employee.setJoindate("20/08/2020");
        employee.setPhoneno("9618984265");
        employee.setSalary("40000");
        EmployeeDTO employeeDTO = departmentMapper.toEmployeeDto(employee);


        Mockito.when(employeeRepo.findById(3)).thenReturn(Optional.of(employee));


        Optional<Employee> employeeOptional = Optional.of(employee);
        log.info("hi testing");
        Optional<EmployeeDTO> employee1 = employeeControllerService.getEmployeeById(3);
        Assertions.assertNotNull(employee1);
        Assertions.assertEquals(employee1.map(EmployeeDTO ::getEmpid), employeeOptional.map(Employee::getEmpid));
      //  Assertions.assertEquals(employee1.map(EmployeeDTO :: getDepartmentDTO, employeeOptional.map(Employee::getDepartment));
       Assertions.assertEquals(employee1.map(EmployeeDTO ::getFirstname), employeeOptional.map(Employee::getFirstname));

    }

    @Test
    public void testGetByIdController() throws Exception{
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MvcResult resultobj = mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/2"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        EmployeeDTO employeeDTO = objectMapper.readValue(resultobj.getResponse().getContentAsString(),EmployeeDTO.class);
        Assertions.assertNotNull(employeeDTO);
        Assertions.assertEquals("duggina",employeeDTO.getFirstname());
        Assertions.assertEquals("40000",employeeDTO.getSalary());

    }




}
