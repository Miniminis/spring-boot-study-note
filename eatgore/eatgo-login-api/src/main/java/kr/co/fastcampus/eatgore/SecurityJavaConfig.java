package kr.co.fastcampus.eatgore;

import kr.co.fastcampus.eatgore.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {

    @Value("${jwt.secret}")
    private String secret;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors().disable()   //실제로는 중요하지만 api 만드는데에는 필요 없어서 disable 처리
                .csrf().disable()
                .formLogin().disable()     //loginForm UI 화면이 안나오도록 처리
                .headers().frameOptions().disable();     //iframe (h2-console web) 관련 처리
    }

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil(secret);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {      //service 에서 ioc 통해서 autowired 로 주입받을 수 있도록
        return new BCryptPasswordEncoder();
    }

}
