package com.colin.controller;

import com.colin.entity.User;
import com.colin.exception.MyException;
import com.colin.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.print.DocFlavor;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public String login(User userLogin,HttpServletRequest request){
        try {
            Subject subject = SecurityUtils.getSubject();//获取当前用户对象
            //生成令牌(传入用户输入的账号和密码)
            UsernamePasswordToken token=new UsernamePasswordToken(userLogin.getUsername(),userLogin.getPassword());
            //这里会加载自定义的realm
            subject.login(token);//把令牌放到login里面进行查询,
            Session session = SecurityUtils.getSubject().getSession();
            User user = (User) session.getAttribute("user");
            return "true";
        } catch (UnknownAccountException e) {
            return "false";
        } catch (IncorrectCredentialsException e) {
            return "false";
        } catch (MyException e){
            return "dongjie";
        }
    }


    @RequestMapping(value = "/changePwd",method = RequestMethod.POST)
    @ResponseBody
    public String changPwd(Integer userid,String oldPassword,String newPassword){
        return userService.changPwd(userid,oldPassword,newPassword) + "";
    }


    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    @ResponseBody
    public String logout(){
        SecurityUtils.getSubject().logout();
        return "true";
    }
    @RequestMapping(value = "/updateByUserId",method = RequestMethod.POST)
    @ResponseBody
    public String updateByUserId(Integer userid, User user){
        userService.updateByUserId(userid,user);
        User newUser = userService.findByUserId(user.getUserid());
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("user",newUser);
        return "true";
    }

    @RequestMapping(value = "/findUserList",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findUserList(){
        Map<String,Object> map = new HashMap<String,Object>();
        List<User> list = userService.findUserList();
        if (list != null && list.size() != 0) {
            map.put("data",list);
            map.put("code", 0);
            map.put("msg","");
            map.put("count",list.size());
        }
        return map;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public String delete(Integer userid,String lastOperator){
        userService.delete(userid,lastOperator);
        return "true";
    }

    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    @ResponseBody
    public String addUser(@Param("user") User user){
        userService.addUser(user);
        return "true";
    }

    /**管理员修改供应商信息
     * @param userid
     * @param user
     * @return
     */
    @RequestMapping(value = "/updateSupplier",method = RequestMethod.POST)
    @ResponseBody
    public String updateSupplier(Integer userid, User user){
        userService.updateByUserId(userid,user);
        return "true";
    }


    /**
     * @return
     */
    @RequestMapping(value = "/SynSupplierBankInfo",method = RequestMethod.POST)
    @ResponseBody
    public String SynSupplierBankInfo(String lastOperator){
        String result = userService.SynSupplierBankInfo(lastOperator);
        return result;
    }
}
