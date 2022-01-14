package com.xiefeihong.video.list.conf

import groovy.util.logging.Slf4j
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Slf4j
@Configuration
@EnableWebSecurity
class SecurityConf extends WebSecurityConfigurerAdapter {

    @Override
    void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers('/**').hasRole('ADMIN')
                .anyRequest().authenticated()
            .and().formLogin()
                .usernameParameter('username')
                .passwordParameter('password')
                .permitAll()
            .and().logout()
                .logoutUrl('/logout')
                .permitAll()
    }

    @Override
    void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                '/css/**'
        )
    }

}
