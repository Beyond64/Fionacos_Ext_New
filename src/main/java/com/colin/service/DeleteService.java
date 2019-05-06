package com.colin.service;

import com.colin.entity.TokenVo;

import java.util.List;

public interface DeleteService {

    void deleteTurnoverHistoey();

    List<TokenVo> findToken();
}
