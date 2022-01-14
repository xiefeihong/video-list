package com.xiefeihong.video.list.conf

import com.xiefeihong.video.list.web.listener.WebAppListener
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

import javax.servlet.ServletRequestListener

@Configuration
class WebConf implements WebMvcConfigurer {

    @Bean
    ServletRequestListener webAppListener(){
        new WebAppListener()
    }

    @Override
    void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/video/")
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE)
    }

}