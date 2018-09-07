package com.colin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.colin.dao.UserMapper;
import com.colin.entity.BankAccountVo;
import com.colin.entity.User;
import com.colin.service.UserService;
import com.colin.tool.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Value("${spring.web.url}")
    private String extUrl;

    @Override
    public User findByName(String name){
        User user = userMapper.findByName(name);
        return user;
    }

    @Override
    public void save(User userlogin){

    }

    @Override
    public void removeByName(String name){

    }

    @Override
    @Transactional
    public void updateByUserId(Integer userid, User user) {
        userMapper.updateByUserId(userid, user);
    }

    @Override
    @Transactional
    public Boolean changPwd(Integer userid, String oldPassword, String newPassword) {
        User user = userMapper.findUserById(userid);
        if(user != null && user.getPassword().equals(oldPassword)){
            userMapper.changPwd(userid,newPassword);
            return true;
        }
        return false;
    }

    @Override
    public User findByUserId(int userid) {
        return userMapper.findUserById(userid);
    }

    @Override
    public List<User> findUserList() {
        List<User> user = userMapper.findUserList();
        return user;
    }

    @Override
    @Transactional
    public void delete(Integer userid, String lastOperator) {
        userMapper.delete(userid,lastOperator);
    }

    @Override
    @Transactional
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    @Override
    @Transactional
    public String SynSupplierBankInfo(String lastOperator) {
        List<String> supplierIds = userMapper.findSupplierIdsList();
        if (supplierIds == null || supplierIds.size() == 0){
            return "fail";
        }
        List<Integer> list = new ArrayList<>();
        Pattern pattern = Pattern.compile("[0-9]*");    //加正则校验是否是数字
        for (int i = 0; i < supplierIds.size(); i++) {
            Matcher isNum = pattern.matcher(supplierIds.get(i));
            if( !isNum.matches() ){
                continue;
            }
            list.add(Integer.parseInt(supplierIds.get(i)));
        }

        String s = JSON.toJSONString(list);
        String url = extUrl + "/gys/findBankAcount";
        JSONObject jsonObject = HttpClientUtils.httpPost(url, s);
        if(jsonObject == null){
            return "false";
        }

        Map<String, Object> innerMap = jsonObject.getInnerMap();
        for (Map.Entry<String,Object> entry : innerMap.entrySet()){
            String supplierId = entry.getKey();
            JSONObject value = (JSONObject) entry.getValue();
            BankAccountVo bankAccountVo = value.toJavaObject(BankAccountVo.class);
            System.out.println(bankAccountVo);
            String bankAccount = bankAccountVo.getBankAccount();
            String bankAddress = bankAccountVo.getBankAddress();
            userMapper.updateSupplierBankInfo(supplierId,bankAccount,bankAddress,lastOperator);
        }
        return "true";
    }

    @Override
    public void saveImagePath(String relativePath, String type, String userName) {
        userMapper.saveImagePath(type,relativePath,userName);
    }

    @Override
    public List<User> findAllEmail() {
        return userMapper.findAllEmail();
    }

    @Override
    public List<User> findCompanyEmail() {
        return userMapper.findCompanyEmail();
    }

    @Override
    public void addEmailHistory(String username, String userEmail, String html) {
        userMapper.addEmailHistory(username,userEmail,html);
    }

}
