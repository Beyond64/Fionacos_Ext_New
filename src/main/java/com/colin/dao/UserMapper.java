package com.colin.dao;

import com.colin.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    User findByName(String name);

    User findUserById(Integer userid);

    void changPwd(@Param("userid") Integer userid, @Param("newPassword") String newPassword);

    void updateByUserId(@Param("userid") Integer userid, @Param("user") User user);

    List<User> findUserList();

    void delete(@Param("userid") Integer userid, @Param("lastOperator") String lastOperator);

    void addUser(@Param("user") User user);

    List<String> findSupplierIdsList();

    void updateSupplierBankInfo(@Param("supplierId") String supplierId, @Param("bankAccount")String bankAccount,
                                @Param("bankAddress")String bankAddress, @Param("lastOperator")String lastOperator);

    void saveImagePath(@Param("type")String type, @Param("relativePath")String relativePath, @Param("userName")String userName);

    List<User> findAllEmail();

    List<User> findCompanyEmail();

    void addEmailHistory(@Param("username") String username, @Param("userEmail")String userEmail, @Param("html")String html);
}
