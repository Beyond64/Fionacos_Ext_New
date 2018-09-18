package com.colin.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.colin.entity.*;
import com.colin.service.SupplierService;
import com.colin.tool.DateUtils;
import com.colin.tool.PathUtil;
import com.colin.tool.UploadUtils;
import com.itextpdf.text.pdf.BaseFont;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class description
 * @version        18/08/28
 * @author         Colin
 */
@RequestMapping("/supplier")
@Controller
public class supplierController {

    @Autowired
    private SupplierService supplierService;

    @RequestMapping(value = "/findItemListByGys")
    @ResponseBody
    public Map<String, Object> findItemListByGys(String  gongyingshang,@RequestParam(value = "date" ,required = false) String date){
        Map<String,Object> map = new HashMap<String,Object>();
        List<ItemVo> list = supplierService.findItemListByGys(date, gongyingshang);
        if(list != null){
            map.put("data",list);
            map.put("code", 0);
            map.put("msg","");
            map.put("count",list.size());
        }
        return map;
    }



    @RequestMapping("/daiXiaoDuiZhang")
    @ResponseBody
    public Map<String, Object> getDaiXiaoDuiZhangList(@RequestParam(value = "date") String date, String gysid){
        Map<String,Object> map = new HashMap<String,Object>();
        if(date == null){
            return map;
        }
        String[] split = date.split("-");
        String year = split[0];
        String month = split[1];
        List<DxCheckVo> list = supplierService.getDaiXiaoDuiZhangList(year, month, gysid);
        if(list != null){
            map.put("data",list);
            map.put("msg","");
            map.put("count",list.size());
        }
        map.put("code", 0);
        return map;
    }

    @RequestMapping("/daiXiaoDuiZhangAll")
    @ResponseBody
    public List<DxCheckVo> getDaiXiaoDuiZhangAllList(String year, String month){
        return supplierService.getDaiXiaoDuiZhangAllList(year,month);
    }


    @RequestMapping("/dxKunCun")
    @ResponseBody
    public Map<String, Object> getDxKunCunList(String gysid, String date){
        Map<String,Object> map = new HashMap<String,Object>();
        List<DxStock> list = supplierService.getDxKunCunList(gysid, date);
        if(list != null){
            map.put("data",list);
            map.put("msg","");
            map.put("count",list.size());
        }
        map.put("code", 0);
        return map;
    }


    @RequestMapping("/dxSunHao")
    @ResponseBody
    public Map<String, Object> getDxSunHaoList(String gysid, String jidu){
        Map<String,Object> map = new HashMap<String,Object>();
        if(jidu != null){
            String[] split = jidu.split("-");
            int year = Integer.parseInt(split[0]);
            int jidu2 = Integer.parseInt(split[1]);
            Map<String, String> jiDuFirstAndLast = DateUtils.getJiDuFirstAndLast(year, jidu2);
            List<DxShunHaoVo> list = supplierService.getDxSunHaoList(gysid, jiDuFirstAndLast.get("start"),jiDuFirstAndLast.get("end"));
            if(list != null){
                map.put("data",list);
                map.put("msg","");
                map.put("count",list.size());
            }
        }
        map.put("code", 0);
        return map;
    }




    @RequestMapping("/exportDaiXiaoDuiZhang")
    @ResponseBody
    public ResultVo exportDaiXiaoDuiZhang(HttpServletRequest request,String date, String gysid){
        try {
            ResultVo resultVo;
                if(date == null){
                    return null;
                }
                String[] split = date.split("-");
                String year = split[0];
                String month = split[1];
                List<DxCheckVo> list = supplierService.getDaiXiaoDuiZhangList(year, month, gysid);
                if(list == null || list.size() == 0){
                    resultVo = new ResultVo("error","无数据,查询月份数据未生成或无代销商品");
                    return resultVo;
                }
                // 获取导出excel指定模版
                TemplateExportParams params = new TemplateExportParams("doc/dxdzdTemple.xlsx");
                //TemplateExportParams params = new TemplateExportParams("D://muban.xlsx");
                Map<String, Object> map = new HashMap<String, Object>();
                Integer jieSuanShuLiangZong = 0;
                Double xiaoShouJinEZong = 0.00;
                Double jieSuanJinEZong = 0.00;
                    for (int i = 0; i < list.size(); i++) {
                        DxCheckVo dxCheckVo = list.get(i);
                        if (dxCheckVo.getJieSuanShuLiang() == null){

                        }else{
                            jieSuanShuLiangZong += Integer.parseInt(dxCheckVo.getJieSuanShuLiang());
                        }
                        if (dxCheckVo.getXiaoShouJinE() == null){

                        }else{
                            xiaoShouJinEZong += Double.parseDouble(dxCheckVo.getXiaoShouJinE());
                        }
                        if (dxCheckVo.getJieSuanJinE() == null){

                        }else{
                            jieSuanJinEZong +=  Double.parseDouble(dxCheckVo.getJieSuanJinE());
                        }
                    }

                    DecimalFormat df = new DecimalFormat("0.00");
                    String xiaoShouJinEZongString = df.format(xiaoShouJinEZong);
                    String jieSuanJinEZongString = df.format(jieSuanJinEZong);
                    map.put("list",list);
                    map.put("date",date);
                    map.put("name",list.get(0).getGysName());
                    map.put("jieSuanShuLiangZong",jieSuanShuLiangZong);
                    map.put("xiaoShouJinEZong",xiaoShouJinEZongString);
                    map.put("jieSuanJinEZong",jieSuanJinEZongString);
                    Workbook workbook = ExcelExportUtil.exportExcel(params, map);
                    String filePath = request.getSession().getServletContext().getRealPath("/" );
                    filePath += "download" + File.separator + gysid + File.separator + "供应商代销对账单" + date + ".xls" ;
                    String relativePath =  "/download" + "/" + gysid + "/" + "供应商代销对账单" +  date + ".xls";
                    File file = new File(filePath);
                    if(!file.getParentFile().exists()){ //如果文件的目录不存在
                        file.getParentFile().mkdirs(); //创建目录
                    }
                    OutputStream output = new FileOutputStream(file);
                    workbook.write(output);
                    resultVo = new ResultVo("success",relativePath);
                    return resultVo;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
    }


    @RequestMapping("/exportDxKunCun")
    @ResponseBody
    public ResultVo exportDxKunCun(HttpServletRequest request,String date, String gysid){
        try {
            ResultVo resultVo;
            if(date == null){
                return null;
            }
            List<DxStock> list = supplierService.getDxKunCunList(gysid, date);
            if(list == null || list.size() == 0){
                resultVo = new ResultVo("error","无数据,查询月份数据未生成或无代销商品");
                return resultVo;
            }
            // 获取导出excel指定模版
            TemplateExportParams params = new TemplateExportParams("doc/dxKunCunTemple.xlsx");
            Map<String, Object> map = new HashMap<String, Object>();
            Integer cssl = 0;
            Integer jxcrsl = 0;
            Integer jxgrsl = 0;
            Integer jysl = 0;

            for (int i = 0; i < list.size(); i++) {
                DxStock DxStock = list.get(i);
                if (DxStock.getDXNQ00() == null){

                }else{
                    cssl += DxStock.getDXNQ00();
                }
                if (DxStock.getDXNQ01() == null){

                }else{
                    jxcrsl += DxStock.getDXNQ01();
                }
                if (DxStock.getDXNQ02() == null){

                }else{
                    jxgrsl +=  DxStock.getDXNQ02();
                }
                if (DxStock.getDXNQREM() == null){

                }else{
                    jysl +=  DxStock.getDXNQREM();
                }
            }

            map.put("list",list);
            map.put("date",date);
            map.put("name",list.get(0).getDXALPH());
            map.put("cssl",cssl);
            map.put("jxcrsl",jxcrsl);
            map.put("jxgrsl",jxgrsl);
            map.put("jysl",jysl);
            Workbook workbook = ExcelExportUtil.exportExcel(params, map);
            String filePath = request.getSession().getServletContext().getRealPath("/" );
            filePath += "download" + File.separator + gysid + File.separator + "代销库存报表" + date + ".xls" ;
            String relativePath =  "/download" + "/" + gysid + "/" + "代销库存报表" +  date + ".xls";
            File file = new File(filePath);
            if(!file.getParentFile().exists()){ //如果文件的目录不存在
                file.getParentFile().mkdirs(); //创建目录
            }
            OutputStream output = new FileOutputStream(file);
            workbook.write(output);
            resultVo = new ResultVo("success",relativePath);
            return resultVo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @RequestMapping("/exportDxSunHao")
    @ResponseBody
    public ResultVo exportDxSunHao(HttpServletRequest request, String gysid, String jidu){
        try {
            ResultVo resultVo;
            if(jidu == null){
                return null;
            }
            String[] split = jidu.split("-");
            int year = Integer.parseInt(split[0]);
            int jidu2 = Integer.parseInt(split[1]);
            Map<String, String> jiDuFirstAndLast = DateUtils.getJiDuFirstAndLast(year, jidu2);
            List<DxShunHaoVo> list = supplierService.getDxSunHaoList(gysid, jiDuFirstAndLast.get("start"),jiDuFirstAndLast.get("end"));
            if(list == null || list.size() == 0){
                resultVo = new ResultVo("error","无数据,查询月份数据未生成或无代销商品");
                return resultVo;
            }
            // 获取导出excel指定模版
            TemplateExportParams params = new TemplateExportParams("doc/DxSunHaoTemple2.xlsx");
            Map<String, Object> map = new HashMap<String, Object>();
            Integer fayunshuliangZong = 0;
            Double gezichengdanZong = 0.00;

            for (int i = 0; i < list.size(); i++) {
                DxShunHaoVo dxShunHaoVo = list.get(i);
                if (dxShunHaoVo.getFayunshuliang() == null){

                }else{
                    fayunshuliangZong += dxShunHaoVo.getFayunshuliang();
                }
                if (dxShunHaoVo.getGezichengdan() == null){

                }else{
                    gezichengdanZong += dxShunHaoVo.getGezichengdan();
                }

            }
            String date = jiDuFirstAndLast.get("start") +" - "+ jiDuFirstAndLast.get("end");
            map.put("list",list);
            map.put("date",date);
            map.put("name",list.get(0).getGongyingshangName());
            map.put("fayunshuliangZong",fayunshuliangZong);
            map.put("gezichengdanZong",gezichengdanZong);
            Workbook workbook = ExcelExportUtil.exportExcel(params, map);
            String filePath = request.getSession().getServletContext().getRealPath("/" );
            filePath += "download" + File.separator + gysid + File.separator + "损耗明细报表" + date + ".xls" ;
            String relativePath =  "/download" + "/" + gysid + "/" + "损耗明细报表" +  date + ".xls";
            File file = new File(filePath);
            if(!file.getParentFile().exists()){ //如果文件的目录不存在
                file.getParentFile().mkdirs(); //创建目录
            }
            OutputStream output = new FileOutputStream(file);
            workbook.write(output);
            resultVo = new ResultVo("success",relativePath);
            return resultVo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    @RequestMapping("/exportServiceList")
    @ResponseBody
    public ResultVo exportServiceList(HttpServletRequest request, Integer gysid, String danjuDate,String gysName,String gysEmail){
        ResultVo resultVo;
        try {
            Configuration freemarkerCfg = new Configuration();
            Map<String, Object> data = this.findServiceList(gysid, danjuDate);
            if(data == null || data.size() == 0 ){
                resultVo = new ResultVo("error","无数据,查询月份数据未生成");
                return resultVo;
            }
            String payType = (String) data.get("payType");
            if(payType != null && "直接付款".equals(payType)){
                data.put("zhijiefukuan","√");
                data.put("dikohuokuan","");
            }
            if(payType != null && "抵扣货款".equals(payType)){
                data.put("zhijiefukuan","");
                data.put("dikohuokuan","√");
            }
            data.put("gysName",gysName);
            data.put("gysId",gysid + "");
            data.put("gysEmail",gysEmail);
            freemarkerCfg.setDirectoryForTemplateLoading(new File(PathUtil.getCurrentPath()));
            freemarkerCfg.setDefaultEncoding("UTF-8");
            Template template = freemarkerCfg.getTemplate("pdf//fwfTemple.html");
            Writer out = new StringWriter();
            template.process(data, out);
            out.flush();
            String content = out.toString();
            ITextRenderer render = new ITextRenderer();
            ITextFontResolver fontResolver = render.getFontResolver();
            fontResolver.addFont("pdf//simhei.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            // 解析html生成pdf
            render.setDocumentFromString(content);
            //解决图片相对路径的问题
            render.layout();
            String filePath = request.getSession().getServletContext().getRealPath("/" );
            filePath += "download" + File.separator + gysid + File.separator + danjuDate+ "服务费清单" + ".pdf" ;
            String relativePath = "/download" + File.separator + gysid + File.separator + danjuDate+ "服务费清单" + ".pdf";
            File file = new File(filePath);
            if(!file.getParentFile().exists()){ //如果文件的目录不存在
                file.getParentFile().mkdirs(); //创建目录
            }
            OutputStream output = new FileOutputStream(file);
            render.createPDF(output);
            resultVo = new ResultVo("success",relativePath);
            return resultVo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/importServiceList")
    @ResponseBody
    public Map<String, Object> importServiceList(MultipartFile file, HttpServletRequest request){
        String code = "0";
        Map<String,Object> map = new HashMap<String,Object>();
        if (file == null){
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(0);
        params.setHeadRows(1);
        List<ServiceListVo> list = null;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), ServiceListVo.class, params);
            if(list != null && list.size() > 0){
                code = supplierService.saveServiceList(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("code",code);
        return map;
    }


    @RequestMapping("/findServiceList")
    @ResponseBody
    public Map<String, Object> findServiceList(Integer gysId,String danjuDate){
        Map<String,Object> map = new HashMap<String,Object>();
        List<ServiceListVo> list = supplierService.findServiceList(gysId,danjuDate);
        Double heji = 0.00;
        String payType = "";
        if (list != null && list.size() > 0 ){
            for (int i = 0; i < list.size(); i++) {
                ServiceListVo serviceListVo = list.get(i);
                if (serviceListVo.getRemarks() == null){
                    serviceListVo.setRemarks("");
                }
                heji += serviceListVo.getAmount();
                payType = serviceListVo.getPayType();
            }
            map.put("heji",heji);
            map.put("list",list);
            map.put("payType",payType);
        }
        return map;

    }



    @RequestMapping(value = "/findFinaceInfoList")
    @ResponseBody
    public Map<String, Object> findFinaceInfoList(){
        Map<String,Object> map = new HashMap<String,Object>();
        List<FinanceVo> list = supplierService.findFinaceInfoList();
        if (list != null && list.size() != 0) {
            map.put("data",list);
            map.put("msg","");
            map.put("count",list.size());
        }
        map.put("code", 0);
        return map;
    }


    @RequestMapping(value = "/addFinaceInfo")
    @ResponseBody
    public String saveFinaceInfo(@Param("user") FinanceVo financeVo){
        String result = supplierService.saveFinaceInfo(financeVo);
        return result;
    }



    @RequestMapping("/importFinaceFile")
    @ResponseBody
    public String importFinaceFile(MultipartFile file, HttpServletRequest request){
        try {
            String name = file.getOriginalFilename();
            String path = request.getSession().getServletContext().getRealPath("\\upload\\" + "finacefile" + "\\");
            String fileName = null;
            fileName = UploadUtils.uploadFile(file,path);
            String relativePath = "/upload/" + "finacefile" + "/"  + fileName;
            supplierService.saveFinaceFileInfo(name,relativePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @RequestMapping(value = "/findFinaceFileList")
    @ResponseBody
    public Map<String, Object> findFinaceFileList(){
        Map<String,Object> map = new HashMap<String,Object>();
        List<FinaceFileInfoVo> list = supplierService.findFinaceFileList();
        if (list != null && list.size() != 0) {
            map.put("data",list);
            map.put("msg","");
            map.put("count",list.size());
        }
        map.put("code", 0);
        return map;
    }

    @RequestMapping(value = "/deleteFinaceInfo")
    @ResponseBody
    public String deleteFinaceInfo(Integer objectId,Integer type){
        supplierService.deleteFinaceInfo(objectId,type);
        return "true";
    }




}


//~ Formatted by Jindent --- http://www.jindent.com
