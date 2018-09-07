package com.colin.dao;

import com.colin.entity.StoreVo;

import java.util.List;

public interface OrderMapper {

    List<StoreVo> findStoreList();
}
