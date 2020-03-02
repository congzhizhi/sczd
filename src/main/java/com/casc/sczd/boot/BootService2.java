package com.casc.sczd.boot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Order(2)
public class BootService2 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("系统启动执行服务2"+ Arrays.toString(args));
    }
}
