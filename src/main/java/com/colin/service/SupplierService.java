package com.colin.service;

import com.colin.entity.*;

import java.util.List;

public interface SupplierService {

    List<ItemVo> findItemListByGys(String date, String gongyingshang);

    List<DxCheckVo> getDaiXiaoDuiZhangList(String year, String month, String gysid);

    List<DxCheckVo> getDaiXiaoDuiZhangAllList(String year, String month);

    List<DxStock> getDxKunCunList(String gysid, String date);

    List<DxShunHaoVo> getDxSunHaoList(String gysid, String startdate, String endDate);

    String saveServiceList(List<ServiceListVo> list);

    List<ServiceListVo> findServiceList(Integer gysId, String danjuDate);
}
