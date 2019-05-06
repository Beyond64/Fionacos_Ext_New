package com.colin.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.colin.dao.SupplierMapper;
import com.colin.entity.*;
import com.colin.service.SupplierService;
import com.colin.tool.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class description
 * @version        18/08/28
 * @author         Colin
 */
@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierMapper supplierMapper;

    @Value("${spring.web.url}")
    private String extUrl;

    @Override
    @Transactional
    public List<ItemVo> findItemListByGys(String date, String gongyingshang) {
        SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
        if ((date == null) || date.equals("")) {
            Date date1 = new Date();
            date = sdf.format(date1);
        }
        //先去本地数据库获取,没有才去查询
        List<ItemVo> historyList = supplierMapper.findTurnoverHistoey(gongyingshang,date);
        if(historyList == null || historyList.size() == 0 ){
            //用来页面展示以及保存到history里面的
            List<ItemVo> arrayList = new ArrayList<>();
            String url = extUrl + "/gys/findTurnoverDay?cban8="+gongyingshang + "&date=" + date;
            String result = HttpClientUtil.doGet(url);
            List<ItemVo>  ItemVos = JSONObject.parseArray(result, ItemVo.class);//把字符串转换成集合
            if (ItemVos != null && ItemVos.size() > 0){
                for (int i = 0; i < ItemVos.size(); i++) {
                    ItemVo itemVo = ItemVos.get(i);
                    if (itemVo.getChsrp1() == null ){
                        continue;
                    }
                    TurnoverDayVo turnoverDayVo = supplierMapper.findBiaoZhunTurnoverDay(itemVo.getChsrp1());
                    if ("GX".equals(itemVo.getImprp3())){
                        itemVo.setBiaoZhunDiTurnoverDay(turnoverDayVo.getGx_low_turnover());
                        itemVo.setBiaoZhunGaoTurnoverDay(turnoverDayVo.getGx_high_turnover());
                    }else{
                        itemVo.setBiaoZhunDiTurnoverDay(turnoverDayVo.getDx_low_turnover());
                        itemVo.setBiaoZhunGaoTurnoverDay(turnoverDayVo.getDx_high_turnover());
                    }
                    itemVo.setChsrp1_desc(turnoverDayVo.getCategory_desc());
                    arrayList.add(itemVo);
                }
            }
            if(arrayList != null && arrayList.size() > 0){
                supplierMapper.saveTurnoverHistoey(arrayList,date);
            }
            return arrayList;
        }else{
            return historyList;
        }
    }

    @Override
    public List<DxCheckVo> getDaiXiaoDuiZhangList(String year, String month, String gysid) {
//        注释的为缓存机制
//        List<DxCheckVo>  list = supplierMapper.findDzdHistoey(gysid,year,month);
//        if(list != null && list.size() > 0){
//            return list;
//        }
        String url = extUrl + "/finance/daiXiaoDuiZhang?year="+year + "&month=" + month + "&gysid=" + gysid;
        String result = HttpClientUtil.doGet(url);
        List<DxCheckVo>  ItemVos = JSONObject.parseArray(result, DxCheckVo.class);//把字符串转换成集合
//        if(ItemVos != null && ItemVos.size() > 0 ){
//            if(ItemVos.get(0).getPosLingShouJia() != null){
//                supplierMapper.saveDzdHistoey(ItemVos,year,month);
//            }
//        }
        return ItemVos;
    }

    @Override
    public List<DxCheckVo> getDaiXiaoDuiZhangAllList(String year, String month) {
        String url = extUrl + "/finance/daiXiaoDuiZhang?year="+year + "&month=" + month;
        String result = HttpClientUtil.doGet(url);
        List<DxCheckVo>  ItemVos = JSONObject.parseArray(result, DxCheckVo.class);//把字符串转换成集合
        return ItemVos;
    }

    @Override
    public List<DxStock> getDxKunCunList(String gysid, String date) {
        String url = extUrl + "/finance/dxKunCun?gysid="+gysid + "&date=" + date;
        String result = HttpClientUtil.doGet(url);
        List<DxStock> dxStocks = JSONObject.parseArray(result, DxStock.class);//把字符串转换成集合
        return dxStocks;
    }

    @Override
    public List<DxShunHaoVo> getDxSunHaoList(String gysid, String startdate, String endDate) {
        String url = extUrl + "/finance/dxShunHao?startDate="+startdate + "&endDate=" + endDate + "&gysid=" + gysid;
        String result = HttpClientUtil.doGet(url);
        List<DxShunHaoVo> list = JSONObject.parseArray(result, DxShunHaoVo.class);//把字符串转换成集合
        return list;
    }

    @Override
    public String saveServiceList(List<ServiceListVo> list) {
        if(list != null && list.size() > 0){
            ServiceListVo serviceListVo = list.get(0);
            List<ServiceListVo> result =  supplierMapper.findServiceList(serviceListVo.getGysId(),serviceListVo.getDanjuDate());
            if (result != null && result.size() > 0){
                return "1";
            }
        }
        supplierMapper.saveServiceList(list);
        return "0";
    }

    @Override
    public List<ServiceListVo> findServiceList(Integer gysId, String danjuDate) {
        List<ServiceListVo> result =  supplierMapper.findServiceList(gysId,danjuDate);
        return result;
    }

    @Override
    public List<FinanceVo> findFinaceInfoList() {
        return supplierMapper.findFinaceInfoList();
    }

    @Override
    public String saveFinaceInfo(FinanceVo financeVo) {
        if (financeVo == null){
            return "false";
        }
        if(financeVo.getObjectId() == null){
            supplierMapper.addFinaceInfo(financeVo);
        }else {
            supplierMapper.updateFinaceInfo(financeVo);
        }
        return "true";
    }

    @Override
    public void saveFinaceFileInfo(String fileName, String relativePath) {
        supplierMapper.saveFinaceFileInfo(fileName,relativePath);
    }

    @Override
    public List<FinaceFileInfoVo> findFinaceFileList() {
        return supplierMapper.findFinaceFileList();
    }

    @Override
    public void deleteFinaceInfo(Integer objectId, Integer type) {
        if(type == 1){
            supplierMapper.deleteFinaceInfo(objectId);
        }else{
            supplierMapper.deleteFinaceFile(objectId);
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
