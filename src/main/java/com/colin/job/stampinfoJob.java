//package com.colin.job;
//
//import com.colin.entity.TokenVo;
//import com.colin.service.DeleteService;
//import com.colin.tool.HttpClientUtil;
//import com.colin.tool.MailUtils;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * Class description
// * @version        18/08/30
// * @author         Colin
// */
//@Component
//public class stampinfoJob {
//
//    @Autowired
//    private DeleteService deleteService;
//
//    /**
//     *
//     */
//    @Scheduled(cron = "0 0 7 * * ?")
//    public void execute() {
//        List<TokenVo> list = deleteService.findToken();
//        if (list != null && list.size() > 0){
//            for (int i = 0; i < list.size(); i++) {
//                TokenVo tokenVo = list.get(i);
//                String url = "https://vip.17wo.cn/api/user/stampInfo";
//                Map<String, String> headers = new HashMap<String,String>();
//                headers.put("Content-Type","application/json;charset=UTF-8");
//                headers.put("Host","vip.17wo.cn");
//                headers.put("Origin","https://vip.17wo.cn");
//                headers.put("Referer","https://vip.17wo.cn/signin/signin.html?id=" + tokenVo.getId());
//                headers.put("token",tokenVo.getToken());
//                headers.put("User-Agent","Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1");
//                headers.put("X-Requested-With","XMLHttpRequest");
//                String result = HttpClientUtil.doPostWithHeaders(url, null, headers);
//                if (result == null || result.indexOf("false") != -1){
//                    MailUtils.SendHtml("purchase","wode19930724@163.com","token已失效",result + "--");
//                }
//            }
//        }
//    }
//}
//
//
////~ Formatted by Jindent --- http://www.jindent.com
