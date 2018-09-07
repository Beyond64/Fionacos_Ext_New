package com.colin.controller;

import com.alibaba.fastjson.JSON;
import com.colin.entity.Params;
import com.colin.entity.StoreVo;
import com.colin.entity.OrderDetailVo;
import com.colin.service.OrderService;
import com.colin.tool.ParseJsonParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/order")
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/barcodeList",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findBarcodeList(String userName){
        Map<String,Object> map = new HashMap<String,Object>();
        ParseJsonParam pjp = new ParseJsonParam();
        String url = "http://183.11.241.154:8081/Ext_interface/ext/gongyingshang/getext_json_1.jsp?gongyingshang=" + userName;
        String json = pjp.loadJSON(url);
        List<Params> list=JSON.parseArray(json,Params.class);
        if (list != null && list.size() != 0) {
            map.put("data",list);
            map.put("code", 0);
            map.put("msg","");
            map.put("count",list.size());
        }
        return map;
    }



    @RequestMapping(value = "/storeList",method = RequestMethod.GET)
    @ResponseBody
    public List<StoreVo> findStoreList(){
        List<StoreVo> list = orderService.findStoreList();
        return list;
    }


    @RequestMapping(value = "/FindBarCodeCount",method = RequestMethod.GET)
    @ResponseBody
    public Integer FindBarCodeCount(String mcu,String litm){
        return orderService.FindBarCodeCount(mcu,litm);
    }

}
