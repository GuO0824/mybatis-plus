package com.feicui.mybatisplus.plus.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.feicui.mybatisplus.plus.unit.FastDFSClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.csource.fastdfs.TrackerClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


/**
 * 文件返回流的形式必须不能是json形式
 */
@Controller
@Api(value = "mybatis-plus 检测swagger的功能", tags = {"文件下载"})
public class FileUpload {


  @GetMapping("/downLoad")
  @ApiOperation(value = "流式文件下载")
  @SentinelResource(value = "upload",fallback = "fallbackMonth")
  public void upload(HttpServletResponse response) throws IOException {
    new RuntimeException("12122");
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
        "attachment;filename=" + new String("流式".getBytes("gbk"), "iso8859-1") + ".xls");
    workbook.write(response.getOutputStream());
    workbook.close();
  }

  //上传文件必须是post请求  fastdfs
  @PostMapping("/upload1")
  @ApiOperation(value = "流式文件上传")
  @ResponseBody
  public String upload1(@RequestParam(name = "file") MultipartFile file) throws Exception {
    //获取文件名
    String name = file.getOriginalFilename();
    int i = name.lastIndexOf(".");
    String substring = name.substring(i + 1);

    FastDFSClient fastDFSClient = new FastDFSClient("client.conf");
    String s = fastDFSClient.uploadFile(file.getBytes(), substring, null);
    System.out.println(s);

    return "fastdfs文件上传成功:" + s;
  }

  @GetMapping("/download")
  @ApiOperation(value = "文件下载fastdfs")
//  @ResponseBody
  public void downloadFastDfs(@ApiParam(value = "文件id")
                              @RequestParam("fileId") String fileId,
      /*@ApiParam(value = "物资id") @RequestParam("fileId") String fileId,*/
                              HttpServletResponse response) throws Exception {
//    String fileId1 = "group1/M00/00/00/wKiuimGxbQ6AbuDNAAM3TPfPRFM411.jpg";
    String[] split = fileId.split("\\.");

    FastDFSClient f = new FastDFSClient("client.conf");
    InputStream inputStream = f.download("group1", fileId);
    System.out.println("文件大小：" + inputStream.available());


//    //将文件写入本地文件
//    String path = System.getProperty("user.dir") + File.separatorChar + "receive";
//    File file = new File(path);
//    if (!file.exists()) {
//      file.mkdir();
//    }
//    //在本地文件中将文件写入0.jpg中
//    FileOutputStream fileOutputStream = new FileOutputStream(path+File.separatorChar+"0.jpg");
//    //定义一个缓冲区
//    byte[] bytes = new byte[1024];
//    while (inputStream.read(bytes) != -1) {
//      fileOutputStream.write(bytes);
//    }
//    fileOutputStream.flush();
//    fileOutputStream.close();
//    inputStream.close();


    //流式下载
    response.setContentType("application/x-msdownload;charset=utf-8");
    response.setCharacterEncoding("UTF-8");
    response.setHeader("Content-Disposition",
        "attachment;filename=" + new String("流式".getBytes("gbk"), "iso8859-1") + "." + split[1]);

    ServletOutputStream outputStream = response.getOutputStream();
    //定义一个缓冲区
    byte[] bytes = new byte[1024];
    while (inputStream.read(bytes) != -1) {
      outputStream.write(bytes);
    }
    outputStream.flush();
    outputStream.close();
    inputStream.close();
  }
  public String fallbackMonth(){
    System.out.println("Sentinel===============异常处理");
    return "Sentinel 异常处理";
  }
}
