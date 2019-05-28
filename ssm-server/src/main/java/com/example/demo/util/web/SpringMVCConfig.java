package com.example.demo.util.web;

//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.FilterType;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * springMVC和spring的配置类
 * 相当于以前的SpringMVC.xml和spring.xml
 * 为了简化，配置写在一个文件里
 *
 * @author wenfs
 */
//@Configuration
//@ComponentScan(basePackages = "com.example.demo", useDefaultFilters = false, includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class)})
//public class SpringMVCConfig extends WebMvcConfigurationSupport {
//
//    /**
//     * 防止@EnableMvc把默认的静态资源路径覆盖了，手动设置的方式
//     * 相当于<mvc:resources mapping="/**" location="/"/>
//     *
//     * @param registry
//     */
//    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        // 解决静态资源无法访问
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
//        // 解决swagger无法访问
//        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
//        // 解决swagger的js文件无法访问
//        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//
//    }
//}

public class SpringMVCConfig{}