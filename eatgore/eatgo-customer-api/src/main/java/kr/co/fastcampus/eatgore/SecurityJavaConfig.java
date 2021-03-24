package kr.co.fastcampus.eatgore;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .cors().disable()   //실제로는 중요하지만 api 만드는데에는 필요 없어서 disable 처리
                .csrf().disable()
                .formLogin().disable()     //loginForm UI 화면이 안나오도록 처리
                .headers().frameOptions().disable();        //iframe (h2-console web) 관련 처리
    }

}
