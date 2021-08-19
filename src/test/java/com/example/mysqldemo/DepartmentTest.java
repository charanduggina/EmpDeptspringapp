package com.example.mysqldemo;


import com.example.mysqldemo.dao.DepartmentRepo;
import com.example.mysqldemo.dto.DepartmentDTO;
import com.example.mysqldemo.dto.EmployeeDTO;
import com.example.mysqldemo.mapper.DepartmentMapper;
import com.example.mysqldemo.model.Department;
import com.example.mysqldemo.service.DepartmentControllerService;
import com.example.mysqldemo.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

@Slf4j
@SpringBootTest
public class DepartmentTest {



    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @InjectMocks
    DepartmentControllerService departmentControllerService;
    @Mock
    DepartmentRepo departmentRepo;


    @Test
    public void testGetDepartmentbyId() throws Exception{
        Department department = new Department();
        department.setDeptnumber(1);
        department.setDeptname("technology");
        department.setLocation("hyderabad");

        Mockito.when(departmentRepo.findById(1)).thenReturn(Optional.of(department));


        Optional<Department> departmentOptional = Optional.of(department);
        log.info("hi testing dept");
        Optional<DepartmentDTO> department1 = departmentControllerService.getDepartmentById(1);
        Assertions.assertNotNull(department1);
        Assertions.assertEquals(department1.map(DepartmentDTO ::getDeptnumber), departmentOptional.map(Department ::getDeptnumber));
        Assertions.assertEquals(department1.map(DepartmentDTO ::getDeptname), departmentOptional.map(Department ::getDeptname));
        Assertions.assertEquals(department1.map(DepartmentDTO ::getLocation), departmentOptional.map(Department ::getLocation));

    }

    @Test
    public void testGetByIdController() throws Exception{
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MvcResult resultobj = mockMvc.perform(MockMvcRequestBuilders.get("/api/department/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        DepartmentDTO departmentDTO = objectMapper.readValue(resultobj.getResponse().getContentAsString(),DepartmentDTO.class);
        Assertions.assertNotNull(departmentDTO);
        Assertions.assertEquals("Technology",departmentDTO.getDeptname());
        Assertions.assertEquals("Bangalore",departmentDTO.getLocation());

    }
}
