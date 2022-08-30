package com.sdnu.iosclub.ucenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description
 * @Author Wang Chen
 * @Date 2021/6/2 21:19
 * @Version 1.0
 **/

@SpringBootApplication
@ComponentScan(basePackages = {"com.sdnu.iosclub"})
@EnableDiscoveryClient
public class UcenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class, args);
    }
}
