package com.feicui.mybatisplus.plus.controller;

import com.feicui.mybatisplus.plus.domain.UserFile;
import com.feicui.mybatisplus.plus.service.UserFileService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;



@Controller
@RequestMapping
public class FileMysqlController {

  @Autowired
  UserFileService userFileService;

  @RequestMapping("/")
  public String login(/*User user, HttpSession session*/){
//    User userDB=userService.login(user);
//    if(userDB!=null){
//      session.setAttribute("user",userDB);
//      return "redirect:/showAll";
//    }else{
      //用户或密码错误重定向回login页面
//    session.setAttribute("user",userDB);
    return "redirect:/showAll";
//    }
  }


  //上传文件的处理 并保持文件信息保存到数据库
  @PostMapping("/upload")
  public String upload(MultipartFile myfile, HttpSession session) throws IOException {

//    //获取上传文件用户id
//    User user = (User) session.getAttribute("user");
    //获取文件的原始名称
    String oldFileName = myfile.getOriginalFilename();
    //获取文件的后缀
    String extension = "." + FilenameUtils.getExtension(myfile.getOriginalFilename());
    //生成新的文件名称
    //randomUUID 使新名称不一样（不起冲突）
    String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
        + UUID.randomUUID().toString().replace("-", "").substring(6) + extension;
    //获取文件大小
    long size = myfile.getSize();
    //文件类型
    String type = myfile.getContentType();
    //处理根据日期生成目录
    String realPath = ResourceUtils.getURL("classpath:").getPath() + "static/files";
    System.out.println(realPath);
    String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    String dateDirPath = realPath + "/" + format;
    File dataDir = new File(dateDirPath);
    if (!dataDir.exists()) dataDir.mkdirs();

    //处理文件上传
    myfile.transferTo(new File(dataDir,newFileName));

    //将文件信息放入数据库保存
    UserFile userFile = new UserFile();
    userFile.setOldFileName(oldFileName);
    userFile.setNewFileName(newFileName);
    userFile.setExt(extension);
    userFile.setSize(String.valueOf(size));
    userFile.setType(type);
    userFile.setPath("/files/" + format);
    userFile.setUserId(1);//1 当前admin用户
    userFileService.save(userFile);
    return "redirect:/showAll";
  }


  //展示用户所有文件数据
  @GetMapping("showAll")
  public String findAll( Model model){
    //在登录的session中获取用户的Id
//    User user= (User) session.getAttribute("user");
    //根据用户id查找有的文件信息
    List<UserFile> byUserId=userFileService.findByUserId();
    //存入作用域中
    model.addAttribute("files",byUserId);
    return "showAll";
  }

}
