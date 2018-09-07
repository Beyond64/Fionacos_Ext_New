package com.colin.service;

import com.colin.entity.EmailVo;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface EmailService {

    Map<String,Object> findEmailList(String date);
}
