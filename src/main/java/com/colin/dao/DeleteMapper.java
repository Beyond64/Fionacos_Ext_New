package com.colin.dao;

import org.apache.ibatis.annotations.Param;

public interface DeleteMapper {

    void deleteTurnoverHistoey(@Param("beforeFiveDay") String beforeFiveDay);
}
