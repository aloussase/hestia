package io.github.aloussase.hestia.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class Config {

    @Value("${dashboard.page.title}")
    private String pageTitle;

}
