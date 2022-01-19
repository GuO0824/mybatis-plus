package com.feicui.mybatisplus.plus.execl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AsynController {

  @Autowired
  private AsyncService asyncService;

  //试试批量导入
  @GetMapping("/async")
  @ResponseBody
  public Integer async(@RequestParam Integer type) throws InterruptedException {
    Integer i = 0;
    if (type == 1) {
      i = asyncService.executeAsync();
    } else if(type==2){
      i = asyncService.executeAsync2();
    }else if(type==3){
      i = asyncService.executeAsync3();
    }
    return i;
  }

}
