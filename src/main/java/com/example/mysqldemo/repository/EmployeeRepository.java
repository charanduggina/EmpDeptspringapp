package com.example.mysqldemo.repository;

import com.example.mysqldemo.Constants.Message;
import com.example.mysqldemo.dto.ResponseDTO;
import org.springframework.stereotype.Service;


@Service
public class EmployeeRepository {
    public ResponseDTO sendMessageemp(String id){
        return ResponseDTO.builder().msg(Message.EMP_LOGIC_MSG_SUCCESS).Empcount(21).build();
    }
}
