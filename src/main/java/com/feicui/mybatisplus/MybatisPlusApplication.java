package com.feicui.mybatisplus;

//import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.util.unit.DataSize;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@EnableSwagger2
//@Import(FdfsClientConfig.class)
public class MybatisPlusApplication {

  @Bean
  public MultipartConfigElement multipartConfigElement() {
    MultipartConfigFactory factory = new MultipartConfigFactory();
    //允许上传的文件最大值
    factory.setMaxFileSize(DataSize.parse("50MB")); //KB,MB
    /// 设置总上传数据总大小
    factory.setMaxRequestSize(DataSize.parse("50MB"));
    return factory.createMultipartConfig();
  }

  public static void main(String[] args) {
    SpringApplication.run(MybatisPlusApplication.class, args);
  }

}
