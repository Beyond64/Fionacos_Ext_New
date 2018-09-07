package com.colin.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.alibaba.fastjson.JSON;
import com.colin.entity.Params;
import com.colin.entity.User;
import com.colin.service.UserService;
import com.colin.tool.ParseJsonParam;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/export")
public class ExportController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/exportGongYingShangSel",method = RequestMethod.GET)
    public void exportGongYingShangSel(OutputStream os,HttpServletResponse response,String datestart, String datestop, String gongyingshang, String area, String mcdc, String brand, String imdsc1)
            throws IOException, WriteException
    {
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(datestart.trim() +"至" + datestop.trim()+ "区域销售/库存报表.xls","UTF-8"));
        WritableWorkbook book = Workbook.createWorkbook(os);

        WritableSheet sheet = book.createSheet("第一页", 0);
        List<String> listStr = new ArrayList();

        listStr.add("门店/仓库");
        listStr.add("供应商编码");
        listStr.add("供应商名称");
        listStr.add("国际条码");
        listStr.add("条码说明");
        listStr.add("库存数量");
        listStr.add("品牌");
        listStr.add("区域");
        listStr.add("周期销售数量");
        listStr.add("周期销售金额");

        WritableFont wf_title = new WritableFont(WritableFont.ARIAL, 9,
                WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
                Colour.BLACK);

        WritableCellFormat wcf_title = new WritableCellFormat(wf_title);
        wcf_title.setBackground(Colour.WHITE);
        wcf_title.setAlignment(Alignment.CENTRE);
        wcf_title.setBorder(Border.ALL,
                BorderLineStyle.THIN, Colour.BLACK);

        WritableCellFormat wcf_title1 = new WritableCellFormat(wf_title);
        wcf_title1.setBackground(Colour.LIGHT_GREEN);
        wcf_title1.setAlignment(Alignment.CENTRE);
        wcf_title1.setBorder(Border.ALL,
                BorderLineStyle.THIN, Colour.BLACK);

        WritableCellFormat wcf_title2 = new WritableCellFormat(wf_title);
        wcf_title2.setBackground(Colour.IVORY);
        wcf_title2.setAlignment(Alignment.CENTRE);
        wcf_title2.setBorder(Border.ALL,
                BorderLineStyle.THIN, Colour.BLACK);

        sheet.setColumnView(0, 35);
        sheet.setColumnView(1, 13);
        sheet.setColumnView(2, 30);
        sheet.setColumnView(3, 30);
        sheet.setColumnView(4, 13);
        sheet.setColumnView(5, 30);
        sheet.setColumnView(6, 13);
        sheet.setColumnView(7, 13);
        sheet.setColumnView(8, 13);
        sheet.setColumnView(9, 13);
        sheet.setColumnView(10, 13);
        sheet.setColumnView(11, 13);
        sheet.setColumnView(12, 13);
        sheet.setColumnView(13, 13);

        int count = 0;
        String desc = "";

        double curclu = 0.0D;
        double quz = 0.0D;
        int jshu = 0;
        for (int j = 0; j < 10; j++)
        {
            Label label = new Label(j, 0, (String)listStr.get(j), wcf_title1);
            sheet.addCell(label);
        }
        ParseJsonParam pjp = new ParseJsonParam();
        String url = "http://183.11.241.154:8081/Ext_interface/ext/gongyingshang/getext_json_sel.jsp?datestart=" +
                datestart.trim() + "&datestop=" + datestop.trim() + "&gongyingshang=" + gongyingshang + "&area=" +
                area + "&mcdc=" + mcdc + "&brand=" + brand + "&imdsc1=" + imdsc1;

        String json = pjp.loadJSON(url);
        List<Params> list=JSON.parseArray(json,Params.class);
        for (Params params : list)
        {
            count++;

            Label label = new Label(0, count, params.getParam2().trim(), wcf_title2);
            sheet.addCell(label);
            label = new Label(1, count, params.getParam3().trim(), wcf_title2);
            sheet.addCell(label);
            label = new Label(2, count, params.getParam4().trim(), wcf_title2);
            sheet.addCell(label);
            label = new Label(3, count, params.getParam5().trim(), wcf_title2);
            sheet.addCell(label);
            label = new Label(4, count, params.getParam6().trim(), wcf_title2);
            sheet.addCell(label);
            Number numb = new Number(5, count, Integer.parseInt(params.getParam9()), wcf_title2);
            sheet.addCell(numb);
            label = new Label(6, count, params.getParam7().trim(), wcf_title2);
            sheet.addCell(label);
            label = new Label(7, count, params.getParam8().trim(), wcf_title2);
            sheet.addCell(label);
            numb = new Number(8, count, Integer.parseInt(params.getParam10()), wcf_title2);
            sheet.addCell(numb);
            numb = new Number(9, count, Double.parseDouble(params.getParam11()), wcf_title2);
            sheet.addCell(numb);
        }
        book.write();
        book.close();
    }

    @RequestMapping(value = "/exportGongYingShangSel_Z",method = RequestMethod.GET)
    public void exportGongYingShangSel_Z(OutputStream os, HttpServletResponse response, String datestart, String datestop, String gongyingshang, String area, String mcdc, String brand, String imdsc1)
            throws IOException, WriteException
    {
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(datestart.trim() +"至" + datestop.trim()+ "区域销售/库存报表(含在途库存).xls","UTF-8"));
        WritableWorkbook book = Workbook.createWorkbook(os);

        WritableSheet sheet = book.createSheet("第一页", 0);
        List<String> listStr = new ArrayList();

        listStr.add("门店/仓库");
        listStr.add("供应商编码");
        listStr.add("供应商名称");
        listStr.add("国际条码");
        listStr.add("条码说明");
        listStr.add("库存数量");
        listStr.add("在途库存");
        listStr.add("品牌");
        listStr.add("区域");
        listStr.add("周期销售数量");
        listStr.add("周期销售金额");

        WritableFont wf_title = new WritableFont(WritableFont.ARIAL, 9,
                WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
                Colour.BLACK);

        WritableCellFormat wcf_title = new WritableCellFormat(wf_title);
        wcf_title.setBackground(Colour.WHITE);
        wcf_title.setAlignment(Alignment.CENTRE);
        wcf_title.setBorder(Border.ALL,
                BorderLineStyle.THIN, Colour.BLACK);

        WritableCellFormat wcf_title1 = new WritableCellFormat(wf_title);
        wcf_title1.setBackground(Colour.LIGHT_GREEN);
        wcf_title1.setAlignment(Alignment.CENTRE);
        wcf_title1.setBorder(Border.ALL,
                BorderLineStyle.THIN, Colour.BLACK);

        WritableCellFormat wcf_title2 = new WritableCellFormat(wf_title);
        wcf_title2.setBackground(Colour.IVORY);
        wcf_title2.setAlignment(Alignment.CENTRE);
        wcf_title2.setBorder(Border.ALL,
                BorderLineStyle.THIN, Colour.BLACK);

        sheet.setColumnView(0, 35);
        sheet.setColumnView(1, 13);
        sheet.setColumnView(2, 30);
        sheet.setColumnView(3, 30);
        sheet.setColumnView(4, 13);
        sheet.setColumnView(5, 30);
        sheet.setColumnView(6, 13);
        sheet.setColumnView(7, 13);
        sheet.setColumnView(8, 13);
        sheet.setColumnView(9, 13);
        sheet.setColumnView(10, 13);
        sheet.setColumnView(11, 13);
        sheet.setColumnView(12, 13);
        sheet.setColumnView(13, 13);

        int count = 0;
        String desc = "";

        double curclu = 0.0D;
        double quz = 0.0D;
        int jshu = 0;
        for (int j = 0; j < 11; j++)
        {
            Label label = new Label(j, 0, (String)listStr.get(j), wcf_title1);
            sheet.addCell(label);
        }
        ParseJsonParam pjp = new ParseJsonParam();
        String url = "http://183.11.241.154:8081/Ext_interface/ext/gongyingshang/getext_json_sel_z.jsp?datestart=" +
                datestart.trim() + "&datestop=" + datestop.trim() + "&gongyingshang=" + gongyingshang + "&area=" +
                area + "&mcdc=" + mcdc + "&brand=" + brand + "&imdsc1=" + imdsc1;

        String json = pjp.loadJSON(url);
        List<Params> list=JSON.parseArray(json,Params.class);
        for (Params params : list)
        {
            count++;

            Label label = new Label(0, count, params.getParam2().trim(), wcf_title2);
            sheet.addCell(label);
            label = new Label(1, count, params.getParam3().trim(), wcf_title2);
            sheet.addCell(label);
            label = new Label(2, count, params.getParam4().trim(), wcf_title2);
            sheet.addCell(label);
            label = new Label(3, count, params.getParam5().trim(), wcf_title2);
            sheet.addCell(label);
            label = new Label(4, count, params.getParam6().trim(), wcf_title2);
            sheet.addCell(label);
            Number numb = new Number(5, count, Integer.parseInt(params.getParam7()), wcf_title2);
            sheet.addCell(numb);
            numb = new Number(6, count, Integer.parseInt(params.getParam12()), wcf_title2);
            sheet.addCell(numb);
            label = new Label(7, count, params.getParam8().trim(), wcf_title2);
            sheet.addCell(label);
            label = new Label(8, count, params.getParam9().trim(), wcf_title2);
            sheet.addCell(label);
            numb = new Number(9, count, Integer.parseInt(params.getParam10()), wcf_title2);
            sheet.addCell(numb);
            numb = new Number(10, count, Double.parseDouble(params.getParam11()), wcf_title2);
            sheet.addCell(numb);
        }
        book.write();
        book.close();
    }

    @RequestMapping(value = "/exportGongYingShangSelByBrand",method = RequestMethod.GET)
    public void exportGongYingShangSelByBrand(OutputStream os,HttpServletResponse response, String datestart, String datestop, String gongyingshang, String brand, String imdsc1)
            throws IOException, WriteException
    {
        String fileName = datestart.trim() + "至" + datestop.trim()+ "品牌销售/库存报表.xls";
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName,"UTF-8"));
        WritableWorkbook book = Workbook.createWorkbook(os);

        WritableSheet sheet = book.createSheet("第一页", 0);
        List<String> listStr = new ArrayList();

        listStr.add("供应商编码");
        listStr.add("供应商名称");
        listStr.add("国际条码");
        listStr.add("条码说明");
        listStr.add("库存数量");
        listStr.add("品牌");
        listStr.add("周期销售数量");
        listStr.add("周期销售金额");

        WritableFont wf_title = new WritableFont(WritableFont.ARIAL, 9,
                WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
                Colour.BLACK);

        WritableCellFormat wcf_title = new WritableCellFormat(wf_title);
        wcf_title.setBackground(Colour.WHITE);
        wcf_title.setAlignment(Alignment.CENTRE);
        wcf_title.setBorder(Border.ALL,
                BorderLineStyle.THIN, Colour.BLACK);

        WritableCellFormat wcf_title1 = new WritableCellFormat(wf_title);
        wcf_title1.setBackground(Colour.LIGHT_GREEN);
        wcf_title1.setAlignment(Alignment.CENTRE);
        wcf_title1.setBorder(Border.ALL,
                BorderLineStyle.THIN, Colour.BLACK);

        WritableCellFormat wcf_title2 = new WritableCellFormat(wf_title);
        wcf_title2.setBackground(Colour.IVORY);
        wcf_title2.setAlignment(Alignment.CENTRE);
        wcf_title2.setBorder(Border.ALL,
                BorderLineStyle.THIN, Colour.BLACK);

        sheet.setColumnView(0, 35);
        sheet.setColumnView(1, 13);
        sheet.setColumnView(2, 30);
        sheet.setColumnView(3, 30);
        sheet.setColumnView(4, 13);
        sheet.setColumnView(5, 13);
        sheet.setColumnView(6, 13);
        sheet.setColumnView(7, 13);
        sheet.setColumnView(8, 13);
        sheet.setColumnView(9, 13);
        sheet.setColumnView(10, 13);
        sheet.setColumnView(11, 13);
        sheet.setColumnView(12, 13);
        sheet.setColumnView(13, 13);

        int count = 0;
        String desc = "";

        double curclu = 0.0D;
        double quz = 0.0D;
        int jshu = 0;
        for (int j = 0; j < 8; j++)
        {
            Label label = new Label(j, 0, (String)listStr.get(j), wcf_title1);
            sheet.addCell(label);
        }
        ParseJsonParam pjp = new ParseJsonParam();
        String url = "http://183.11.241.154:8081/Ext_interface/ext/gongyingshang/getext_json_kucun.jsp?datestart=" +
                datestart.trim() + "&datestop=" + datestop.trim() + "&gongyingshang=" + gongyingshang + "&brand=" + brand + "&imdsc1=" + imdsc1;

        String json = pjp.loadJSON(url);
        List<Params> list=JSON.parseArray(json,Params.class);
        for (Params params : list)
        {
            count++;
            Label label = new Label(0, count, params.getParam1().trim(), wcf_title2);
            sheet.addCell(label);
            label = new Label(1, count, params.getParam2().trim(), wcf_title2);
            sheet.addCell(label);
            label = new Label(2, count, params.getParam3().trim(), wcf_title2);
            sheet.addCell(label);
            label = new Label(3, count, params.getParam4().trim(), wcf_title2);
            sheet.addCell(label);
            Number numb = new Number(4, count, Integer.parseInt(params.getParam5()), wcf_title2);
            sheet.addCell(numb);
            label = new Label(5, count, params.getParam6().trim(), wcf_title2);
            sheet.addCell(label);
            numb = new Number(6, count, Integer.parseInt(params.getParam7()), wcf_title2);
            sheet.addCell(numb);
            numb = new Number(7, count, Double.parseDouble(params.getParam8()), wcf_title2);
            sheet.addCell(numb);
        }
        book.write();
        book.close();
    }

    @RequestMapping(value = "/exportGongYingShangSelByBrand_Z",method = RequestMethod.GET)
    public void exportGongYingShangSelByBrand_Z(OutputStream os,HttpServletResponse response, String datestart, String datestop, String gongyingshang, String brand, String imdsc1)
            throws IOException, WriteException
    {
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(datestart.trim() +"至" + datestop.trim()+ "品牌销售/库存报表(含在途库存).xls","UTF-8"));
        WritableWorkbook book = Workbook.createWorkbook(os);

        WritableSheet sheet = book.createSheet("第一页", 0);
        List<String> listStr = new ArrayList();

        listStr.add("供应商编码");
        listStr.add("供应商名称");
        listStr.add("国际条码");
        listStr.add("条码说明");
        listStr.add("库存数量");
        listStr.add("在途库存");
        listStr.add("品牌");
        listStr.add("周期销售数量");
        listStr.add("周期销售金额");

        WritableFont wf_title = new WritableFont(WritableFont.ARIAL, 9,
                WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
                Colour.BLACK);

        WritableCellFormat wcf_title = new WritableCellFormat(wf_title);
        wcf_title.setBackground(Colour.WHITE);
        wcf_title.setAlignment(Alignment.CENTRE);
        wcf_title.setBorder(Border.ALL,
                BorderLineStyle.THIN, Colour.BLACK);

        WritableCellFormat wcf_title1 = new WritableCellFormat(wf_title);
        wcf_title1.setBackground(Colour.LIGHT_GREEN);
        wcf_title1.setAlignment(Alignment.CENTRE);
        wcf_title1.setBorder(Border.ALL,
                BorderLineStyle.THIN, Colour.BLACK);

        WritableCellFormat wcf_title2 = new WritableCellFormat(wf_title);
        wcf_title2.setBackground(Colour.IVORY);
        wcf_title2.setAlignment(Alignment.CENTRE);
        wcf_title2.setBorder(Border.ALL,
                BorderLineStyle.THIN, Colour.BLACK);

        sheet.setColumnView(0, 35);
        sheet.setColumnView(1, 13);
        sheet.setColumnView(2, 30);
        sheet.setColumnView(3, 30);
        sheet.setColumnView(4, 13);
        sheet.setColumnView(5, 13);
        sheet.setColumnView(6, 13);
        sheet.setColumnView(7, 13);
        sheet.setColumnView(8, 13);
        sheet.setColumnView(9, 13);
        sheet.setColumnView(10, 13);
        sheet.setColumnView(11, 13);
        sheet.setColumnView(12, 13);
        sheet.setColumnView(13, 13);

        int count = 0;
        String desc = "";

        double curclu = 0.0D;
        double quz = 0.0D;
        int jshu = 0;
        for (int j = 0; j < 9; j++)
        {
            Label label = new Label(j, 0, (String)listStr.get(j), wcf_title1);
            sheet.addCell(label);
        }
        ParseJsonParam pjp = new ParseJsonParam();
        String url = "http://183.11.241.154:8081/Ext_interface/ext/gongyingshang/getext_json_kucun_z.jsp?datestart=" +
                datestart.trim() + "&datestop=" + datestop.trim() + "&gongyingshang=" + gongyingshang + "&brand=" + brand + "&imdsc1=" + imdsc1;

        String json = pjp.loadJSON(url);
        List<Params> list=JSON.parseArray(json,Params.class);
        for (Params params : list)
        {
            count++;
            Label label = new Label(0, count, params.getParam1().trim(), wcf_title2);
            sheet.addCell(label);
            label = new Label(1, count, params.getParam2().trim(), wcf_title2);
            sheet.addCell(label);
            label = new Label(2, count, params.getParam3().trim(), wcf_title2);
            sheet.addCell(label);
            label = new Label(3, count, params.getParam4().trim(), wcf_title2);
            sheet.addCell(label);
            Number numb = new Number(4, count, Integer.parseInt(params.getParam5()), wcf_title2);
            sheet.addCell(numb);
            numb = new Number(5, count, Integer.parseInt(params.getParam9()), wcf_title2);
            sheet.addCell(numb);
            label = new Label(6, count, params.getParam6().trim(), wcf_title2);
            sheet.addCell(label);
            numb = new Number(7, count, Integer.parseInt(params.getParam7()), wcf_title2);
            sheet.addCell(numb);
            numb = new Number(8, count, Double.parseDouble(params.getParam8()), wcf_title2);
            sheet.addCell(numb);
        }
        book.write();
        book.close();
    }


    @RequestMapping("/supplier")
    public void exportXls(HttpServletRequest request, HttpServletResponse response
            , ModelMap map) {
        try {
        List<User> userList = userService.findUserList();
        String file_name = "供应商信息列表.xls";
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + file_name);

        ExportParams exportParams = new ExportParams();
        org.apache.poi.ss.usermodel.Workbook workbook     = ExcelExportUtil.exportExcel(exportParams, User.class, userList);
        file_name = new String(file_name.getBytes(), "ISO-8859-1");
        workbook.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
