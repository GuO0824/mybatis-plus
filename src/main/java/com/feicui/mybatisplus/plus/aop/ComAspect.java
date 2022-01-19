package com.feicui.mybatisplus.plus.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class ComAspect {

  /**
   * 注解Pointcut切入点
   * 定义出一个或一组方法，当执行这些方法时可产生通知
   * 指向你的切面类方法
   * 由于这里使用了自定义注解所以指向你的自定义注解
   */
  @Pointcut("@annotation(com.feicui.mybatisplus.plus.contants.SystemCrmlog)")
  public void crmAspect() {

  }

  /**
   *抛出异常后通知（@AfterThrowing）：方法抛出异常退出时执行的通知
   * 注意在这里不能使用ProceedingJoinPoint
   * 不然会报错ProceedingJoinPoint is only supported for around advice
   * throwing注解为错误信息
   * @param joinPoint
   * @param ex
   */
//  @AfterThrowing(value="crmAspect()", throwing="ex")
//  public void afterThrowingMethod(JoinPoint joinPoint, Exception ex) throws Exception {
//    HttpServletRequest httpServletRequest = getHttpServletRequest();
//    //获取管理员用户信息\
//    WebUtil webUtil = new WebUtil();
//    Map<String, Object> user = webUtil.getUser(httpServletRequest);
//    CrmLogMessage log=new CrmLogMessage();
//    //获取需要的信息
//    String context=getServiceMthodDescription(joinPoint);
//    String usr_name="";
//    String rolename="";
//    if(user!=null){
//      usr_name = user.get("usr_name").toString();
//      rolename=user.get("rolename").toString();
//    }
//    //管理员姓名
//    log.setUserName(usr_name);
//    //角色名
//    log.setUserRole(rolename);
//    //日志信息
//    log.setContent(usr_name+context);
//    //设置参数集合
//    log.setRemarks(getServiceMthodParams(joinPoint));
//    //设置表名
//    log.setTableName(getServiceMthodTableName(joinPoint));
//    //操作时间
//    SimpleDateFormat sif=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    log.setDateTime(sif.format(new Date()));
//    //设置ip地址
//    log.setIp(httpServletRequest.getRemoteAddr());
//    //设置请求地址
//    log.setRequestUrl(httpServletRequest.getRequestURI());
//    //执行结果
//    log.setResult("执行失败");
//    //错误信息
//    log.setExString(ex.getMessage());
//    //将数据保存到数据库
//    Sys_logDao sysLogDao=new Sys_logDao();
//    sysLogDao.addSys_log(log);
//  }


}
