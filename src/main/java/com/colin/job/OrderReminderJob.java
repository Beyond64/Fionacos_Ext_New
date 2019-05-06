package com.colin.job;

import com.colin.entity.ItemStockVo;
import com.colin.entity.User;
import com.colin.service.OrderService;
import com.colin.tool.DateUtils;
import com.colin.tool.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Class description
 * @version        18/08/30
 * @author         Colin
 */
@Component
public class OrderReminderJob {

    @Value("${spring.web.url}")
    private String extUrl;

    @Autowired
    private OrderService orderService;

    /**
     * 每两周查看两周未自主下采购单的供应商,查条码库存,如果低于50则发邮件提醒
     */
    @Scheduled(cron = "0 15 10 ? * MON")
    public void execute() {
        Date date = new Date();
        Date dayBefore = DateUtils.getDayBefore(date, -30);
        Date dayBeforeTwo = DateUtils.getDayBefore(date, -20);
        String dateEnd = new SimpleDateFormat("yyyy-MM-dd").format(date);
        String dateStart = new SimpleDateFormat("yyyy-MM-dd").format(dayBefore);
        String dateNoOrder = new SimpleDateFormat("yyyy-MM-dd").format(dayBeforeTwo);
        List<User> userList = orderService.findGysNoOrder(dateNoOrder);
        if (userList != null && userList.size() > 0){
            for (int i = 0; i < userList.size(); i++) {
                User user = userList.get(i);
                String username = user.getUsername();
                String email1 = user.getEmail();
                String nickname = user.getNickname();
                List<ItemStockVo> list = orderService.findItemList(username,"100010001",dateStart,dateEnd);
                if(list != null && list.size() > 0 ){
                    for (int j = 0; j < list.size(); j++) {
                        ItemStockVo itemStockVo = list.get(j);
                        Integer zongCangStock = itemStockVo.getZongCangStock();
                        String imlitm = itemStockVo.getImlitm();
                        if(zongCangStock != null && zongCangStock < 50){
                            if(null != email1){
                                String html = getHTML(nickname);
                                String title = "[库存预警]供应商两周未下达订单";
                                MailUtils.SendHtml("purchase",email1,title,html);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }




    public static String getHTML(String storeName){
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
        sb.append("    <table style=\"width:800px; border-spacing:0;\">  ");
//        邮件头
        sb.append("       <tr style=\"height: 20px;\">");
        sb.append("         <td colspan=\"4\">库存预警:</td>  ");
        sb.append("       </tr>  ");


        //以上开始填充数据
        // 邮件尾
        sb.append("       <tr style=\"height: 20px;\">");
        sb.append("         <td colspan=\"4\">贵公司部分单品已在我司仓库库存不足,请及时安排下单,以免影响销售</td>  ");
        sb.append("       </tr>  ");
        sb.append("       <tr style=\"height: 20px;\">");
        sb.append("         <td colspan=\"4\">如有疑问，请及时与我司订货专员沟通,谢谢!</td>  ");
        sb.append("       </tr>  ");
        sb.append("       <tr style=\"height: 20px;\">");
        sb.append("         <td colspan=\"4\">&nbsp;&nbsp;&nbsp;&nbsp;</td>  ");
        sb.append("       </tr>  ");
        sb.append("       <tr style=\"height: 20px;\">");
        sb.append("         <td colspan=\"4\">妍丽-采购部</td>  ");
        sb.append("       </tr>  ");
        //        邮件尾
        sb.append("    </table> ");
        sb.append("     </body>");
        sb.append("    </html>");

        return sb.toString();
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
