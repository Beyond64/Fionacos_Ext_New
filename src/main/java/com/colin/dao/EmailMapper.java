package com.colin.dao;

import com.colin.entity.EmailVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface EmailMapper {

    List<EmailVo> findEmailList(@Param("date") String date);
}
