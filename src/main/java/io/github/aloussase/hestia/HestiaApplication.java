package io.github.aloussase.hestia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class HestiaApplication {

    public static void main(String[] args) {
        SpringApplication.run(HestiaApplication.class, args);
    }

}
