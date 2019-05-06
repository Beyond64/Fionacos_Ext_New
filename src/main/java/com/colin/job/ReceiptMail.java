package com.colin.job;

import com.alibaba.fastjson.JSONObject;
import com.colin.entity.ItemVo;
import com.colin.entity.ReceiptVo;
import com.colin.entity.User;
import com.colin.service.SupplierService;
import com.colin.service.UserService;
import com.colin.tool.DateUtils;
import com.colin.tool.HttpClientUtil;
import com.colin.tool.MailUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class ReceiptMail {


    @Autowired
    private UserService userService;

    @Autowired
    private SupplierService supplierService;

    @Value("${spring.web.url}")
    private String extUrl;

    @Scheduled(cron = "0 0 9 * * ?")
    public void execute() {
        List<User> companyEmail = userService.findCompanyEmail();
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        String julian = DateUtils.dateToJuLian(date) + "";
        if(companyEmail != null && companyEmail.size() > 0){
            for (int i = 0; i < companyEmail.size(); i++) {
                User user = companyEmail.get(i);
                String username = user.getUsername();
                String userEmail = user.getEmail();
                List<ReceiptVo> receiptVos = this.findReceiptForJde(username, julian);
                if(null == receiptVos || receiptVos.size() == 0){
                    continue;
                }
                String html = getHTML(receiptVos);
//                System.out.println(html);
                String title = "【妍丽】仓库来货上架明细";
                MailUtils.SendHtml("purchase",userEmail,title,html);
                //去记录邮件发送历史
                userService.addEmailHistory(username,userEmail,html);
            }
        }
    }



    @Scheduled(cron = "0 0 3 ? * WED")
    //定时发送周转天邮件
    public void execute2() {
        List<User> companyEmail = userService.findCompanyEmail();
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date dayBefore = DateUtils.getDayBefore(date, -1);
        String format = sdf.format(dayBefore);
        if(companyEmail != null && companyEmail.size() > 0){
            for (int i = 0; i < companyEmail.size(); i++) {
                User user = companyEmail.get(i);
                String username = user.getUsername();
                String userEmail = user.getEmail();
                List<ItemVo> itemListByGys = supplierService.findItemListByGys(format, username);
                if(null == itemListByGys || itemListByGys.size() == 0){
                    continue;
                }
                String html = getHTML2(itemListByGys);
//                System.out.println(html);
                String title = "【妍丽】库存周转天";
//                String copyTo = "sweety_li@fionacos.com;harlin_huang@fionacos.com;yuki_yang@fionacos.com;sophie_zhang@fionacos.com;rinco_he@fionacos.com;dolly_dong@fionacos.com;marah_lei@fionacos.com;joanna_lai@fionacos.com; ivan_huang@fionacos.com;elly_weng@fionacos.com;sarah_huang@fionacos.com;sunshine_zhao@fionacos.com;olivia_lin@fionacos.com;vivi_wu@fionacos.com";
                String copyTo = "";
                MailUtils.SendEmailCopyTo("purchase",userEmail,copyTo,title,html);
                //去记录邮件发送历史
                userService.addEmailHistory(username,userEmail,html);
            }
        }
    }

    public List<ReceiptVo> findReceiptForJde(String pcan8,String pcdgl){
        String url = extUrl + "/gys/findReceipt?pcan8="+pcan8 + "&pcdgl=" + pcdgl;
        String result = HttpClientUtil.doGet(url);
        List<ReceiptVo>  ReceiptVos = JSONObject.parseArray(result, ReceiptVo.class);//把字符串转换成集合
        return ReceiptVos;
    }


    public static String getHTML(List<ReceiptVo> list){
        StringBuilder sb=new StringBuilder();
        sb.append("    <html>");
        sb.append("     <head>");
        sb.append("      <title> New Document </title>");
        sb.append("      <meta charset=\"utf-8\">");
        sb.append("     </head>");
        sb.append("    ");
        sb.append("    <style type=\"text/css\">");
        sb.append("    table { ");
        sb.append("      margin: 10px 0 10px 0;");
        sb.append("    }");
        sb.append("    ");
        sb.append("    table caption { ");
        sb.append("      text-align:left;");
        sb.append("    }");
        sb.append("    ");
        sb.append("    body { ");
        sb.append("      font-family:\"Microsoft YaHei\";");
        sb.append("    }");
        sb.append("    table tr th { ");
        sb.append("      background: #3B3B3B;");
        sb.append("      color: #FFF;");
        sb.append("      padding: 1px 1px;");
        sb.append("      text-align: left;");
        sb.append("    }");
        sb.append("    ");
        sb.append("    table tr td { ");
        sb.append("      color: #FFF;");
        sb.append("      padding: 1px 1px;");
        sb.append("      text-align: left;");
        sb.append("    }");
        sb.append("    ");
        sb.append("    table tr.odd{");
        sb.append("        background-color:#cef;");
        sb.append("    }");
        sb.append("    ");
        sb.append("    table tr.even{");
        sb.append("        background-color:#ffc;");
        sb.append("    }");
        sb.append("      ");
        sb.append("    table tr td { ");
        sb.append("      color: #47433F;");
        sb.append("      border-top: 1px solid #FFF;");
        sb.append("    }");
        sb.append("     </style>");
        sb.append("    ");
        sb.append("     <body>");
        sb.append("    <table style=\"width:1100px; border-spacing:0;\">  ");
//        邮件头
        sb.append("       <tr style=\"height: 20px;\">");
        sb.append("         <td colspan=\"9\">Dear，<br>&nbsp;&nbsp;, 仓库来货上架明细如下，如有疑问请3个工作日内与我司采购/订单员联系，谢谢！</td>  ");
        sb.append("       </tr>  ");
//        邮件头
        sb.append("       <tr style=\"height: 20px;\">  ");
        sb.append("          <th>采购单号</th>  ");
        sb.append("          <th>国际条码</th>  ");
        sb.append("          <th>品名</th>  ");
        sb.append("          <th>订单数量</th>  ");
        sb.append("          <th>实收数量</th>  ");
        sb.append("          <th>残旧数量</th>  ");
        sb.append("          <th>渗漏数量</th>  ");
        sb.append("          <th>效期数量</th>  ");
        sb.append("          <th>正常数量</th>  ");
        sb.append("       </tr>  ");
        //以上开始填充数据
        for (int i = 0; i < list.size(); i++) {
            ReceiptVo receiptVo = list.get(i);
            sb.append("       <tr class=\"odd\" style=\"height: 20px;\">");
            sb.append("         <td>"+ receiptVo.getPCDOCO()+"-"+ receiptVo.getPCDCTO()+"</td>  ");
            sb.append("         <td>"+ receiptVo.getPCLITM()+"</td>  ");
            sb.append("         <td>"+ receiptVo.getIMDSC1().trim()+"</td>  ");
            sb.append("         <td>"+ receiptVo.getPCUORG()/1000+"</td>  ");
            sb.append("         <td>"+ receiptVo.getPCUREC()/1000+"</td>  ");
            sb.append("         <td>"+ receiptVo.getPCQTYS()/1000+"</td>  ");
            sb.append("         <td>"+ receiptVo.getPCQTYJ()/1000+"</td>  ");
            sb.append("         <td>"+ receiptVo.getPCQTYC()/1000+"</td>  ");
            sb.append("         <td>"+ receiptVo.getPCAQTY()/1000+"</td>  ");
            sb.append("       </tr>  ");
        }
        //以上开始填充数据
        // 邮件尾
        sb.append("       <tr style=\"height: 20px;\">");
        sb.append("         <td colspan=\"9\">妍丽仓储物流部</td>  ");
        sb.append("       </tr>  ");
        //        邮件尾
        sb.append("    </table> ");
        sb.append("     </body>");
        sb.append("    </html>");

        return sb.toString();
    }

    public static String getHTML2(List<ItemVo> list){
        StringBuilder sb=new StringBuilder();
        sb.append("    <html>");
        sb.append("     <head>");
        sb.append("      <title> New Document </title>");
        sb.append("      <meta charset=\"utf-8\">");
        sb.append("     </head>");
        sb.append("    ");
        sb.append("    <style type=\"text/css\">");
        sb.append("    table { ");
        sb.append("      margin: 10px 0 10px 0;");
        sb.append("    }");
        sb.append("    ");
        sb.append("    table caption { ");
        sb.append("      text-align:left;");
        sb.append("    }");
        sb.append("    ");
        sb.append("    body { ");
        sb.append("      font-family:\"Microsoft YaHei\";");
        sb.append("    }");
        sb.append("    table tr th { ");
        sb.append("      background: #3B3B3B;");
        sb.append("      color: #FFF;");
        sb.append("      padding: 1px 1px;");
        sb.append("      text-align: left;");
        sb.append("      font-size: 12px;");
        sb.append("    }");
        sb.append("    ");
        sb.append("    table tr td { ");
        sb.append("      color: #FFF;");
        sb.append("      padding: 1px 1px;");
        sb.append("      text-align: left;");
        sb.append("      font-size: 12px;");
        sb.append("    }");
        sb.append("    ");
        sb.append("    table tr.odd{");
        sb.append("        background-color:#cef;");
        sb.append("    }");
        sb.append("    ");
        sb.append("    table tr.even{");
        sb.append("        background-color:#ffc;");
        sb.append("    }");
        sb.append("      ");
        sb.append("    table tr td { ");
        sb.append("      color: #47433F;");
        sb.append("      border-top: 1px solid #FFF;");
        sb.append("    }");
        sb.append("     </style>");
        sb.append("    ");
        sb.append("     <body>");
        //邮件头
        sb.append("       <div>\n" +
                "            <span style=\"font-size: 15px\">\n" +
                "                 Dear，\n" +
                "                    <br>\n" +
                "                    &nbsp;&nbsp;库存周转天如下表，如有疑问请与我司采购/订货员联系，谢谢！\n" +
                "            </span>\n" +
                "        </div>");
        sb.append("    <table style=\"width:1000px; border-spacing:0;\">  ");
//        邮件头
        sb.append("       <tr style=\"height: 20px;\">  ");
        sb.append("          <th>周转天</th>  ");
        sb.append("          <th>国际条码</th>  ");
        sb.append("          <th>条码描述</th>  ");
        sb.append("          <th>品类</th>  ");
        sb.append("          <th>品类描述</th>  ");
        sb.append("          <th>代购销</th>  ");
        sb.append("          <th>供应商码</th>  ");
        sb.append("          <th>供应商名称</th>  ");
        sb.append("       </tr>  ");
        //以上开始填充数据
        for (int i = 0; i < list.size(); i++) {
            ItemVo itemVo = list.get(i);
            sb.append("       <tr class=\"odd\" style=\"height: 20px;\">");
            if(itemVo.getTurnoverDay() > itemVo.getBiaoZhunGaoTurnoverDay()){
                sb.append("         <td><div style=\"background-color: #FF4500;width: 30px;height: 30px\" }>&nbsp;</div></td>  ");
            }else if(itemVo.getTurnoverDay() < itemVo.getBiaoZhunDiTurnoverDay()){
                sb.append("         <td><div style=\"background-color: #FFFF00;width: 30px;height: 30px\" }>&nbsp;</div></td>  ");
            } else{
                sb.append("         <td><div style=\"background-color: #00FF7F;width: 30px;height: 30px\" }>&nbsp;</div></td>  ");
            }
            sb.append("         <td>"+ itemVo.getCblitm()+"</td>  ");
            sb.append("         <td>"+ itemVo.getImdsc1()+"</td>  ");
            sb.append("         <td>"+ itemVo.getChsrp1()+"</td>  ");
            sb.append("         <td>"+ itemVo.getChsrp1_desc()+"</td>  ");
            sb.append("         <td>"+ itemVo.getImprp3()+"</td>  ");
            sb.append("         <td>"+ itemVo.getCban8()+"</td>  ");
            sb.append("         <td>"+ itemVo.getAbalky()+"</td>  ");
            sb.append("       </tr>  ");
        }
        //以上开始填充数据
        sb.append("    </table> ");
        // 邮件尾
        sb.append("       <div>\n" +
                "        <span style=\"font-size: 15px\">周转天颜色描述:</span><br>\n" +
                "        <span style=\"font-size: 15px\">&nbsp;&nbsp;&nbsp;&nbsp;<span style=\"background-color: #00FF7F\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>绿色：周转天正常</span><br>\n" +
                "        <span style=\"font-size: 15px\">&nbsp;&nbsp;&nbsp;&nbsp;<span style=\"background-color: #FF4500\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>红色：周转天偏高，业绩表现不佳，须提高销售业绩</span><br>\n" +
                "        <span style=\"font-size: 15px\">&nbsp;&nbsp;&nbsp;&nbsp;<span style=\"background-color: #FFFF00\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>黄色：周转天偏低，即将有缺货风险:</span>\n" +
                "    </div>");
        sb.append("     </body>");
        sb.append("    </html>");

        return sb.toString();
    }


}
