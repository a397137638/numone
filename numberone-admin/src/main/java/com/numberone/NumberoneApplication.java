package com.numberone;

import com.bstek.ureport.console.UReportServlet;
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

/**
 * 启动程序
 * 
 * @author numberone
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class })
@ImportResource("classpath:ureport-console-context.xml")
@MapperScan("com.numberone.*.mapper")
public class NumberoneApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(NumberoneApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  Numberone启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }

    @Bean //定义ureport的启动servlet
    public ServletRegistrationBean buildUreportServlet(){
        return new ServletRegistrationBean(new UReportServlet(), "/ureport/*");
    }
}