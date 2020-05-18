package com.feicui.mybatisplus.plus.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 文件返回流的形式必须不能是json形式
 */
@Controller
@Api(value = "mybatis-plus 检测swagger的功能", tags = {"文件下载"})
public class FileUpload {


  @GetMapping("/upload")
  @ApiOperation(value = "流式文件下载")
  public void upload(HttpServletResponse response) throws IOException {
    HSSFWorkbook workbook = new HSSFWorkbook();
    // 创建一个sheet页
    HSSFSheet sheet = workbook.createSheet("指标模板");
    HSSFRow row = sheet.createRow(0);
    for (int i = 0; i < 10; i++) {
      HSSFCell cell1 = row.createCell(i);
      cell1.setCellValue(i);
    }
    response.setContentType("application/x-msdownload;charset=utf-8");
    response.setCharacterEncoding("UTF-8");
    response.setHeader("Content-Disposition",
        "attachment;filename="+new String("流式".getBytes("gbk"), "iso8859-1")+".xls");
    workbook.write(response.getOutputStream());
    workbook.close();
  }
}
