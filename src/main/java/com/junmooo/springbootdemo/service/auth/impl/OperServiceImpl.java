package com.junmooo.springbootdemo.service.auth.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.junmooo.springbootdemo.entity.auth.Operator;
import com.junmooo.springbootdemo.mapper.auth.OperatorMapper;
import com.junmooo.springbootdemo.service.auth.OperService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

@Service
public class OperServiceImpl implements OperService {

    @Autowired
    private OperatorMapper operatorMapper;

    @Override
    public Operator getOperatorByOpername(String operName) throws Exception {
        QueryWrapper<Operator> wrapper = new QueryWrapper<>();
        wrapper.eq("OPER_NAME", operName);
        try {
            return operatorMapper.selectOne(wrapper);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    @Override
    public List<Operator> getOperList(Operator operator) throws Exception {
        QueryWrapper<Operator> wrapper = new QueryWrapper<>();
        String operEmail = operator.getOperEmail();
        String operName = operator.getOperName();
        String operStatus = operator.getOperStatus();
        String deleteFlag = operator.getDeleteFlag();
        String phoneNo = operator.getPhoneNo();
        String remark = operator.getRemark();
        assembleNotNull(wrapper,"OPER_EMAIL",operEmail);
        assembleNotNull(wrapper,"OPER_NAME",operName);
        assembleNotNull(wrapper,"OPER_STATUS",operStatus);
        assembleNotNull(wrapper,"DELETE_FLAG",deleteFlag);
        assembleNotNull(wrapper,"PHONE_NO",phoneNo);
        assembleNotNull(wrapper,"remark",remark);
        List<Operator> operators = operatorMapper.selectList(wrapper);
        return operators;
    }

    @Override
    public int delOper(String operId) throws Exception {
        QueryWrapper<Operator> wrapper = new QueryWrapper<>();
        wrapper.eq("OPER_ID", operId);
        int i = operatorMapper.delete(wrapper);
        return i;
    }

    private QueryWrapper assembleNotNull(QueryWrapper wrapper, String eqKey, String eqVal){
        if (StringUtils.isNotEmpty(eqVal)){
            wrapper.eq(eqKey, eqVal);
        }
        return wrapper;
    }
    @Override
    public int updateOpers(Operator operator) throws Exception {
        UpdateWrapper<Operator> wrapper = new UpdateWrapper<>();
        String id = operator.getOperId();
        wrapper.eq("OPER_ID",id);
        int i = operatorMapper.update(operator,wrapper);
        return i;
    }

    public Operator addOperator(Operator operator) throws Exception {
        operator.setTimeCreated(new Date(Calendar.getInstance().getTimeInMillis()));
        operator.setOperId(UUID.randomUUID().toString());
        int insert = operatorMapper.insert(operator);
        if (insert == 1) {
            return operator;
        }
        throw new Exception("user insert failed");
    }
}
