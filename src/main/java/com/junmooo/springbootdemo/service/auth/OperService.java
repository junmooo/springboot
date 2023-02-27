package com.junmooo.springbootdemo.service.auth;

import com.junmooo.springbootdemo.entity.auth.Operator;

import java.util.List;

public interface OperService {

    int updateOpers(Operator operator) throws Exception;

    Operator addOperator(Operator operator) throws Exception;

    Operator getOperatorByOpername(String operName) throws Exception;

    List<Operator> getOperList(Operator operator) throws Exception;

    int delOper(String operId) throws Exception;
}
