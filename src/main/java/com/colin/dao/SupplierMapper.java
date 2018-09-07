package com.colin.dao;

import com.colin.entity.DxCheckVo;
import com.colin.entity.ItemVo;
import com.colin.entity.TurnoverDayVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SupplierMapper {

    TurnoverDayVo findBiaoZhunTurnoverDay(@Param("chsrp1") String chsrp1);

    List<ItemVo> findTurnoverHistoey(@Param("gongyingshang") String gongyingshang, @Param("date") String date);

    void saveTurnoverHistoey(@Param("list") List<ItemVo> list, @Param("date") String date);

    void saveDzdHistoey(@Param("list") List<DxCheckVo> list, @Param("year") String year, @Param("month") String month);

    List<DxCheckVo> findDzdHistoey(@Param("gysid") String gysid, @Param("year") String year, @Param("month") String month);
}
