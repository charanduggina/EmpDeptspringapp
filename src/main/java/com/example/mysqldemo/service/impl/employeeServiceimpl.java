package com.example.mysqldemo.service.impl;

import com.example.mysqldemo.Constants.Message;
import com.example.mysqldemo.dto.ResponseDTO;
import com.example.mysqldemo.repository.EmployeeRepository;
import com.example.mysqldemo.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class employeeServiceimpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public ResponseDTO testlogic(Boolean Success){
        if(Boolean.TRUE.equals(Success)){
            log.info("executing success employee logic ");
            return employeeRepository.sendMessageemp(null);

        }
        else{
            log.info("executing failed employee logic ");
            return ResponseDTO.builder().msg(Message.EMP_LOGIC_MSG_FAILED).build();
        }

    }
}
