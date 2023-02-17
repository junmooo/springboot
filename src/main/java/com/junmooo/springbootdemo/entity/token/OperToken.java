package com.junmooo.springbootdemo.entity.token;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OperToken {
    private String operId;
    private String operName;
    private String operEmail;
}
