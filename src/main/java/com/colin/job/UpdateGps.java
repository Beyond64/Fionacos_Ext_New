package com.colin.job;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.colin.entity.GaoDeVo;
import com.colin.entity.GpsVo;
import com.colin.entity.StoreVo;
import com.colin.service.UserService;
import com.colin.tool.HttpClientUtil;
import com.colin.tool.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * [简要描述]:
 * [详细描述]:<br/>
 *
 * @author Colin[刘洋]
 * @version 1.0, 2019年03月20日
 * @since JDK1.8
 */
@Component
public class UpdateGps {

    @Autowired
    private UserService userService;

    public void execute() {
        String url = "https://restapi.amap.com/v3/place/text?s=rsv3&children=&key=8325164e247e15eea68b59e89200988b&page=1&offset=10&city=440300&language=zh_cn&callback=jsonp_965024_&platform=JS&logversion=2.0&sdkversion=1.3&appname=https%3A%2F%2Flbs.amap.com%2Fconsole%2Fshow%2Fpicker&csid=E75B2E4B-F0D3-4986-9292-40B4C577730E&keywords=";
        List<String> list = userService.findStroreName();
        if (!CollectionUtils.isEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                String store = list.get(i);
                String replace = store.replace("·", "");
                String replace1 = replace.replace(" ", "");
                System.out.println("参数:" + replace1 );
                String s = HttpClientUtil.doGet(url + replace1);
                s = s.replace("jsonp_965024_(","");
                s = s.replace("})","}");
                GaoDeVo gaoDeVo = JSON.parseObject(s,GaoDeVo.class);
                if(gaoDeVo == null || gaoDeVo.getPois() == null || gaoDeVo.getPois().length <= 0){
                    String so = store.replace("店", "");
                    String s1 = HttpClientUtil.doGet(url + so);
                    s = s1.replace("jsonp_965024_(","");
                    s = s1.replace("})","}");
                    gaoDeVo = JSON.parseObject(s,GaoDeVo.class);
                }

                if(gaoDeVo == null || gaoDeVo.getPois() == null || gaoDeVo.getPois().length <= 0){
                    continue;
                }
                GpsVo[] pois = gaoDeVo.getPois();
                String location = pois[0].getLocation();
                String[] split = location.split(",");
                String longitude = split[0];
                String latitude = split[1];
                userService.updateGpsByName(store,longitude,latitude);
                System.out.println(store+ "已更新");
            }
        }
        System.out.println("更新完毕");
    }
}
