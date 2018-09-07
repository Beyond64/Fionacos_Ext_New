package com.colin.service.impl;

import com.colin.dao.EmailMapper;
import com.colin.entity.EmailVo;
import com.colin.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailMapper emailMapper;

    @Override
    public Map<String,Object> findEmailList(String date) {
        List<EmailVo> list = emailMapper.findEmailList(date);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("data",list);
        map.put("code", 0);
        map.put("msg","");
        map.put("count",list.size());
        return map;
    }
}
