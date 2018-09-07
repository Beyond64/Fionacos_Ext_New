package com.colin.service.impl;

import com.colin.dao.DeleteMapper;
import com.colin.service.DeleteService;
import com.colin.tool.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class DeleteServiceImpl implements DeleteService {

    @Autowired
    private DeleteMapper deleteMapper;

    @Override
    @Transactional
    public void deleteTurnoverHistoey() {
        //获取前五天的时间
        SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String beforeFiveDay = sdf.format(DateUtils.getDayBefore(date,-5));
        deleteMapper.deleteTurnoverHistoey(beforeFiveDay);
    }
}
