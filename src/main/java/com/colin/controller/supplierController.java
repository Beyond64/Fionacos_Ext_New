package com.colin.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.colin.entity.*;
import com.colin.service.SupplierService;
import com.colin.tool.DateUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
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

}


//~ Formatted by Jindent --- http://www.jindent.com
