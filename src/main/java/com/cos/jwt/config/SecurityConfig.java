package com.cos.jwt.config;


import com.cos.jwt.config.jwt.JwtAuthenticationFilter;
import com.cos.jwt.filter.AuthorizationFilter;
import com.cos.jwt.config.jwt.JwtAuthorizaionFilter;
import com.cos.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;


@Configuration
@EnableWebSecurity //security 활성화, 웹 설정을 하는 어노테이션 Configuration과 같이 사용
@RequiredArgsConstructor // final 키워드가 붙으면 생성자를 만들어 주는 lombok어노테이션
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;

    private final UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(new AuthorizationFilter(), BasicAuthenticationFilter.class);
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // session 사용설정 제거
                .and()
                .addFilter(corsFilter)
                .formLogin().disable()
                .httpBasic().disable() // session에 ID, PW를 담아서 보내는 방식 (barer방식을 사용하여 사용자 인증가능)
                .addFilter(new JwtAuthenticationFilter(authenticationManager())) //form 로그인을 사용하지 않고 특정 방식을 사용하여 로그인을 시도를 위한 필터
                .addFilter(new JwtAuthorizaionFilter(authenticationManager(),userRepository)) // jwt token 유효성 검증을 위한 필터
                .authorizeRequests()
                .antMatchers("/api/v1/user/**")
                .access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/v1/manager/**")
                .access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/v1/admin/**")
                .access("hasRole('ROLE_ADMIN')")
                .anyRequest()
                .permitAll();
    }

}
