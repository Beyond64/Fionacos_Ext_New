package com.colin.service;

import com.colin.entity.User;

import java.util.List;

public interface UserService {

    //根据名字查找用户
    User findByName(String name);

    //保存用户登录信息
    void save(User user);

    //根据姓名删除
    void removeByName(String name);

    //根据用户id更新
    void updateByUserId(Integer userid, User user);

    //修改密码
    Boolean changPwd(Integer userid, String oldPassword, String newPassword);

    //根据id查询用户
    User findByUserId(int userid);

    List<User> findUserList();

    void delete(Integer userid, String lastOperator);

    void addUser(User user);

    String SynSupplierBankInfo(String lastOperator);

    void saveImagePath(String relativePath, String type, String userName);

    List<User> findAllEmail();

    List<User> findCompanyEmail();

    void addEmailHistory(String username, String userEmail, String html);

    List<String> findStroreName();

    void updateGpsByName(String store, String longitude, String latitude);
}
