package com.colin.dao;

import com.colin.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SupplierMapper {

    TurnoverDayVo findBiaoZhunTurnoverDay(@Param("chsrp1") String chsrp1);

    List<ItemVo> findTurnoverHistoey(@Param("gongyingshang") String gongyingshang, @Param("date") String date);

    void saveTurnoverHistoey(@Param("list") List<ItemVo> list, @Param("date") String date);

    void saveDzdHistoey(@Param("list") List<DxCheckVo> list, @Param("year") String year, @Param("month") String month);

    List<DxCheckVo> findDzdHistoey(@Param("gysid") String gysid, @Param("year") String year, @Param("month") String month);

    void saveServiceList(@Param("list") List<ServiceListVo> list);

    List<ServiceListVo> findServiceList(@Param("gysId") Integer gysId, @Param("danjuDate") String danjuDate);

    List<FinanceVo> findFinaceInfoList();

    void addFinaceInfo(@Param("financeVo") FinanceVo financeVo);

    void updateFinaceInfo(@Param("financeVo") FinanceVo financeVo);

    void saveFinaceFileInfo(@Param("fileName") String fileName, @Param("relativePath") String relativePath);

    List<FinaceFileInfoVo> findFinaceFileList();

    void deleteFinaceInfo(@Param("objectId") Integer objectId);

    void deleteFinaceFile(@Param("objectId") Integer objectId);
}
