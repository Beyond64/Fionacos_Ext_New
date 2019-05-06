package com.colin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.colin.dao.OrderMapper;
import com.colin.entity.*;
import com.colin.service.OrderService;
import com.colin.tool.DateUtils;
import com.colin.tool.HttpClientUtil;
import com.colin.tool.ParseJsonParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public ResultVo saveSupplierOrder(List<OrderVo> supplierOrderList, String mcu) {
        long timeMillis = System.currentTimeMillis();
        String julian = DateUtils.dateToJuLian(new Date())+"";
        ResultVo resultVo = new ResultVo();
        String url = extUrl + "/purchase/findMcuInfo?mcu="+ mcu;
        String mcuResult = HttpClientUtil.doGet(url);
        StoreVo  storeVo = JSON.parseObject(mcuResult,StoreVo.class);
        if(supplierOrderList != null && supplierOrderList.size() > 0){
            for (int i = 0; i < supplierOrderList.size(); i++) {
                OrderVo orderVo = supplierOrderList.get(i);
                orderVo.setTmukid(timeMillis);
                orderVo.setTmlnid((i + 1) * 1000);
                orderVo.setTmlitm(orderVo.getImlitm().trim());
                orderVo.setTmmcu("   "+ mcu);
                orderVo.setTmshan(storeVo.getStoreId());
                orderVo.setTman8(orderVo.getCban8());
                orderVo.setTmdrqj(julian);
                orderVo.setTmtrdj(julian);
                int uorge1 = Integer.parseInt(orderVo.getBarCodeCount());
                orderVo.setTmuorge1(uorge1 * 1000);
                orderVo.setTmuser("COLIN");
                orderVo.setTmupmj(julian);
                orderVo.setTmedsp("N");
            }
            String json = JSON.toJSONString(supplierOrderList);
            Map<String, String> map = new HashMap<>();
            map.put("supplierOrderList",json);
            map.put("mcu",mcu);
            String result = HttpClientUtil.doPost(extUrl + "/purchase/saveSupplierOrder", map);
            if(result != null && "success".equals(result)){
                saveOrderInfoToLocal(supplierOrderList,storeVo.getStoreName());
            }
            resultVo.setState("success");
        }else{
            resultVo.setState("err");
            resultVo.setMsg("json序列化失败");
        }
        return resultVo;
    }

    @Override
    public List<OrderVo> findOrderDetails(Long orderNumber, String orderCreator) {
        String url = extUrl + "/purchase/findOrderDetails?OrderNumber="+orderNumber + "&orderCreator=" + orderCreator;
        String result = HttpClientUtil.doGet(url);
        List<OrderVo>  orderVos = JSONObject.parseArray(result, OrderVo.class);//把字符串转换成集合
        return orderVos;
    }

    @Override
    public List<OrderHeadVo> findOrderHeadList(String orderCreator,String orderStatus) {
        if(orderStatus != null && "".equals(orderStatus)){
            orderStatus = null;
        }
        List<OrderHeadVo>  orderHeadVos = orderMapper.findOrderHeadList(orderCreator,orderStatus);
        return orderHeadVos;
    }

    @Override
    public List<OrderHeadVo> findPendingOrders() {
        return orderMapper.findPendingOrders();
    }

    @Override
    public void updateOrder(OrderVo orderVo) {
        String tmedsp = orderVo.getTmedsp();
        OrderHeadVo orderHeadVo = new OrderHeadVo();

        orderHeadVo.setOrderStatus(tmedsp);
        orderHeadVo.setOrderNumber(orderVo.getTmukid());
        orderHeadVo.setOrderApprovalTime(new Date());
        orderHeadVo.setPurchaseOrderNo(orderVo.getTmdoco());
        orderHeadVo.setPurchaseOrderType(orderVo.getTmdcto());
        orderMapper.updateOrder(orderHeadVo);
    }

    @Override
    public Map<String, Object> exportOrderInfo(String orderCreator, Long orderNumber) {
        HashMap<String, Object> map = new HashMap<>();
        OrderHeadVo orderHeadVo = orderMapper.findOrderHead(orderNumber,orderCreator);
        if(orderHeadVo != null){
            String deliveryToDesc = orderHeadVo.getDeliveryToDesc();
            Long orderNumber1 = orderHeadVo.getOrderNumber();
            Date orderApprovalTime = orderHeadVo.getOrderApprovalTime();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            String format = sdf.format(orderApprovalTime);
            Long purchaseOrderNo = orderHeadVo.getPurchaseOrderNo();
            String purchaseOrderType = orderHeadVo.getPurchaseOrderType();
            List<OrderVo> list = findOrderDetails(orderNumber, orderCreator);
            if(list != null && list.size() > 0){
                for (int i = 0; i < list.size(); i++) {
                    OrderVo orderVo = list.get(i);
                    Integer tmuorg = orderVo.getTmuorg();
                    if(tmuorg != null){
                        orderVo.setTmuorg(tmuorg/1000);
                    }
                }
            }
            map.put("list",list);
            map.put("deliveryToDesc",deliveryToDesc);
            map.put("orderNumber1",orderNumber1);
            map.put("orderApprovalTime",format);
            map.put("purchaseOrderNo",purchaseOrderNo);
            map.put("purchaseOrderType",purchaseOrderType);
        }
        return map;
    }

    @Override
    public ResultVo updateOrderHeader(Long orderNo, String trackingNanme, String trackingNo) {
        ResultVo resultVo = new ResultVo();
        if (null != orderNo && null != trackingNanme && null != trackingNo){
            OrderHeadVo orderHeadVo = new OrderHeadVo();
            orderHeadVo.setOrderNumber(orderNo);
            orderHeadVo.setTrackingName(trackingNanme);
            orderHeadVo.setTrackingNumber(trackingNo);
            orderHeadVo.setOrderStatus("F");
            orderMapper.updateOrder(orderHeadVo);
            resultVo.setState("success");
        }else {
            resultVo.setState("err");
            resultVo.setMsg("参数为空");
        }
        return resultVo;

    }

    @Override
    public List<ItemStockVo> findItemList(String userName,String mcu, String dateStart, String dateEnd) {
        String url = extUrl + "/purchase/findItemStock?and8="+userName + "&mcu=" + mcu + "&dateStart=" + dateStart + "&dateEnd=" + dateEnd;
        String result = HttpClientUtil.doGet(url);
        List<ItemStockVo>  itemStockVos = JSONObject.parseArray(result, ItemStockVo.class);//把字符串转换成集合
        return itemStockVos;
    }

    @Override
    public List<User> findGysNoOrder(String dateNoOrder) {
        return orderMapper.findGysNoOrder(dateNoOrder);
    }

    @Override
    public ResultVo saveBuHuoCanShu(Integer username, Integer xiadanzhouqi, Integer songhuozhouqi) {
        ResultVo resultVo = new ResultVo();
        OrderCanShuVo buHuoCanShu = findBuHuoCanShu(username);
        if(null == buHuoCanShu){
            orderMapper.saveBuHuoCanShu(username,xiadanzhouqi,songhuozhouqi);
            resultVo.setMsg("保存成功");
            resultVo.setState("success");
        }else{
            orderMapper.updateBuHuoCanShu(username,xiadanzhouqi,songhuozhouqi);
            resultVo.setMsg("更新成功");
            resultVo.setState("success");
        }
        return resultVo;
    }

    @Override
    public OrderCanShuVo findBuHuoCanShu(Integer username) {
        return orderMapper.findBuHuoCanShu(username);
    }

    @Override
    public void saveOrderMuBan(List<ItemStockVo> list, String username) {
        orderMapper.saveOrderMuBan(list,username);
    }

    @Override
    public void deleteOrderMuBan(String username) {
        orderMapper.deleteOrderMuBan(username);
    }

    @Override
    public List<ItemStockVo> findOrderMuBan(String userName) {
        return orderMapper.findOrderMuBan(userName);
    }

    @Override
    public PageVo findJdeOrderList(String userName, Integer page, Integer limit) {
        PageVo pageVo = new PageVo();
        pageVo.setPage(page);
        pageVo.setLimit(limit);
        String Counturl = extUrl + "/purchase/findJdeOrderListCount?userName="+userName;
        String Countresult = HttpClientUtil.doGet(Counturl);
        int count = 0;
        if (Countresult != null && !"".equals(Countresult) ){
            count = Integer.parseInt(Countresult);
        }
        pageVo.setCount(count);
        Integer starRows = pageVo.getStarRows();
        Integer endRows = pageVo.getEndRows();

        String url = extUrl + "/purchase/findJdeOrderList?userName="+userName + "&starRows="+ starRows+"&endRows="+endRows;
        String result = HttpClientUtil.doGet(url);
        List<JdeOrderHeardVo>  list = JSONObject.parseArray(result, JdeOrderHeardVo.class);//把字符串转换成集合
        pageVo.setData(list);
        return pageVo;

    }

    @Override
    public List<JdeOrderDetailVo> findJdeOrderDetails(Long orderNo,String orderType) {
        String url = extUrl + "/purchase/findJdeOrderDetails?orderNo=" + orderNo + "&orderType=" + orderType;
        String result = HttpClientUtil.doGet(url);
        List<JdeOrderDetailVo>  list = JSONObject.parseArray(result, JdeOrderDetailVo.class);//把字符串转换成集合
        return list;
    }

    //byjore
    @Override
    public  List<JdeOrderMothDetail>  findJdeOrderMothDetails(String starTime, String endTime, String companyId,String supplierId,String choBoxvalue) {
        //获取表格里面的数据
        String url = extUrl + "/purchase/findJdeOrderMounthList?starTime="+starTime+"&endTime="+endTime+"&companyId="+companyId+"&supplierId="+supplierId+"&choBoxvalue="+choBoxvalue;
        String result = HttpClientUtil.doGet(url);
        System.out.println("获取返回的jason数据"+result);
        List<JdeOrderMothDetail>  list = JSONObject.parseArray(result, JdeOrderMothDetail.class);//把字符串转换成集合
        return list;
    }
    @Async
    public void saveOrderInfoToLocal(List<OrderVo> supplierOrderList,String mcuName){
        OrderHeadVo orderHeadVo = new OrderHeadVo();
        OrderVo orderVo = supplierOrderList.get(0);
        orderHeadVo.setDeliveryToDesc(mcuName);
        orderHeadVo.setOrderNumber(orderVo.getTmukid());
        orderHeadVo.setProductsNumber(supplierOrderList.size());
        orderHeadVo.setDeliveryTo(orderVo.getTmshan());
        orderHeadVo.setOrderCreator(orderVo.getTman8());
        orderHeadVo.setOrderStatus(orderVo.getTmedsp());
        orderMapper.saveOrderHead(orderHeadVo);
//        orderMapper.saveOrderDetails(supplierOrderList);
    }
}
