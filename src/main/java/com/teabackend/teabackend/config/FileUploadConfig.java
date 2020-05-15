package com.teabackend.teabackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author shuyang
 * @Description:
 * @create 2020-05-12 23:23
 */
//@Configuration
public class FileUploadConfig extends WebMvcConfigurationSupport {

    String url = "/file/**";
    String path = "file:///D:/tea/business/798620/temp/";

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(url).addResourceLocations(path);
        super.addResourceHandlers(registry);
    }
}
