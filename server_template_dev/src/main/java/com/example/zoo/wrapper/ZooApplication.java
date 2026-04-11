package com.example.zoo.wrapper;

import com.example.zoo.ass2.manage.Manage;
import com.example.zoo.wrapper.service.ZooService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZooApplication {
    public static ZooService manage;

    public static void main(String[] args) {
        SpringApplication.run(ZooApplication.class, args);
        manage = new Manage();
        manage.init();
    }
}
