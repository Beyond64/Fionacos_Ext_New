package com.colin.service.impl;

import com.alibaba.fastjson.JSON;
import com.colin.dao.OrderMapper;
import com.colin.entity.OrderDetailVo;
import com.colin.entity.StoreVo;
import com.colin.service.OrderService;
import com.colin.tool.ParseJsonParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Value("${spring.web.url}")
    private String extUrl;

    @Override
    public List<StoreVo> findStoreList() {
        return orderMapper.findStoreList();
    }

    @Override
    public Integer FindBarCodeCount(String mcu, String litm) {
        ParseJsonParam pjp = new ParseJsonParam();
        String url = extUrl + "/gys/findF4102Info?mcu=" + mcu +"&litm=" + litm;
        String json = pjp.loadJSON(url);
        JSON toJSON = (JSON) JSON.toJSON(json);
        if(null == toJSON || "".equals(toJSON)){
            return 0;
        }
        OrderDetailVo orderDetailVo = JSON.toJavaObject(toJSON, OrderDetailVo.class);
        return orderDetailVo.getIBMULT();
    }
}
