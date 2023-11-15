package com.frostholl.financeCalcBackend.config;

import com.frostholl.financeCalcBackend.util.DateFormatter;
import com.frostholl.financeCalcBackend.util.PercentFormatter;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.number.PercentStyleFormatter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        WebMvcConfigurer.super.addFormatters(registry);
        registry.addFormatter(dateTimeFormatter());
        //registry.addFormatter(percentStyleFormatter());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);
        //registry.addResourceHandler("/style/**").addResourceLocations("/style/");
    }

    @Bean
    public DateFormatter dateTimeFormatter() {
        return new DateFormatter();
    }

//    @Bean
//    public PercentStyleFormatter percentStyleFormatter() {
//        return new PercentFormatter();
//    }
}
