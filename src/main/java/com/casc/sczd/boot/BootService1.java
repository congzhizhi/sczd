package com.casc.sczd.boot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class BootService1 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("系统启动执行服务1");
    }
}
