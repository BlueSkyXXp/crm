package com.stone;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 启动程序
 * 
 * @author ruoyi
 */

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class StoneApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(StoneApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  STONE-CRM启动成功   ლ(´ڡ`ლ)ﾞ ");
    }
}
