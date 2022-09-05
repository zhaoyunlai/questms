package com.sdnu.iosclub.qvs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Konjacer
 * @create 2022-09-05 11:45
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.sdnu.iosclub"})
@EnableDiscoveryClient
public class QvsApplication {
    public static void main(String[] args) {
        SpringApplication.run(QvsApplication.class, args);
    }
}
