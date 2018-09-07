//package com.colin.controller;
//
//import cn.afterturn.easypoi.excel.ExcelExportUtil;
//import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
//import com.colin.entity.DxCheckVo;
//import com.colin.entity.ItemVo;
//import com.colin.service.SupplierService;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.*;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * Class description
// * @version        18/08/28
// * @author         Colin
// */
//@RequestMapping("/download")
//@Controller
//public class DownloadController {
//
//    @RequestMapping(value = "/downloadFile")
//    public void downloadFile(HttpServletRequest req, HttpServletResponse resp,String path,String downloadName) {
//        download(resp,path,downloadName);
//
//    }
//
//
//
//    /**
//     * @param resp
//     * @param path         文件路径
//     * @param downloadName 文件下载时名字
//     */
//    public static void download(HttpServletResponse resp, String path, String downloadName) {
//        String fileName = null;
//        try {
//            fileName = new String(downloadName.getBytes("GBK"), "ISO-8859-1");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        File file = new File(path);
//        resp.reset();
//        resp.setContentType("application/octet-stream");
//        resp.setCharacterEncoding("utf-8");
//        resp.setContentLength((int) file.length());
//        resp.setHeader("Content-Disposition", "attachment;filename=" + fileName);
//        byte[] buff = new byte[1024];
//        BufferedInputStream bis = null;
//        OutputStream os = null;
//        try {
//            os = resp.getOutputStream();
//            bis = new BufferedInputStream(new FileInputStream(file));
//            int i = 0;
//            while ((i = bis.read(buff)) != -1) {
//                os.write(buff, 0, i);
//                os.flush();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                bis.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//
//
//}
//
//
////~ Formatted by Jindent --- http://www.jindent.com
