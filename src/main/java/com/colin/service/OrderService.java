package com.colin.service;

import com.colin.entity.StoreVo;

import java.util.List;

public interface OrderService {
    List<StoreVo> findStoreList();

    Integer FindBarCodeCount(String mcu, String litm);
}
