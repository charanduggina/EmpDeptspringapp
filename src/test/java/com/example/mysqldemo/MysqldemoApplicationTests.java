package com.example.mysqldemo;

import com.example.mysqldemo.Constants.Message;
import com.example.mysqldemo.dto.ResponseDTO;
import com.example.mysqldemo.repository.EmployeeRepository;
import com.example.mysqldemo.service.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

@SpringBootTest
class MysqldemoApplicationTests {
	@Autowired
	private EmployeeService employeeService;

	/*
	@Test
	void contextLoads() {
		ResponseDTO responseDTO = employeeService.testlogic(false);
		Assertions.assertNotNull(responseDTO);
		Assertions.assertEquals(responseDTO.getMsg(), Message.EMP_LOGIC_MSG_FAILED);
	}

	 */

	@Test
	void employeelogicwithMock() {
		EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);

		Mockito.when(employeeRepository.sendMessageemp(null)).thenReturn(ResponseDTO.builder().msg(Message.EMP_LOGIC_MSG_SUCCESS).Empcount(20).build());

		ReflectionTestUtils.setField(employeeService,"employeeRepository",employeeRepository);
		ResponseDTO responseDTO = employeeService.testlogic(true);
		Assertions.assertNotNull(responseDTO);
		Assertions.assertEquals(responseDTO.getMsg(), Message.EMP_LOGIC_MSG_SUCCESS);
		Assertions.assertEquals(20,responseDTO.getEmpcount());
	}

}
