package com.zwd.boot;

import lombok.extern.slf4j.Slf4j;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@Slf4j
@MapperScan(basePackages="com.zwd.boot.mapper")
@SpringBootApplication
public class SpringbootApiQuickerApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringbootApiQuickerApplication.class, args);
        String serverPort = context.getEnvironment().getProperty("server.port");
        log.info("SpringbootApiQuicker started at http://localhost:" + serverPort);
    }

}
