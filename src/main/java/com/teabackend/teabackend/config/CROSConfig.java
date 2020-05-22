package com.teabackend.teabackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author shuyang
 * @Description:
 * @create 2020-04-20 16:03
 */
@Configuration
public class CROSConfig extends WebMvcConfigurationSupport {


    static String url = "/**";
    static String fileVisitHead = "file:///";
    public static String systemPath = "";

    static {
        String systemType = System.getProperties().getProperty("os.name");
        if ("Windo".equals(systemType.substring(0,5))){
            System.out.println("Windows系统");
            System.out.println("systemPath:"+systemPath);
            systemPath = "D:/tea/";
            System.out.println("systemPath:"+systemPath);
        }else if ("Linux".equals(systemType.substring(0,5))){
            System.out.println("Linux系统");
            System.out.println("systemPath:"+systemPath);
            systemPath = "/home/tea";
            System.out.println("systemPath:"+systemPath);
        }
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler(url).addResourceLocations(fileVisitHead+systemPath);
        super.addResourceHandlers(registry);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH")
                .allowCredentials(true).maxAge(3600);
    }

}
