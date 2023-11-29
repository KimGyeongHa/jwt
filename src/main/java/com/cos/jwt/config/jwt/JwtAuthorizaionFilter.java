package com.cos.jwt.config.jwt;

import com.auth0.jwt.JWT;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizaionFilter extends BasicAuthenticationFilter {
    public JwtAuthorizaionFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

   @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("권한 url 접근");

        String jwt_token = request.getHeader("jwt_token").replace("barer ","");

        if(jwt_token != null){
            //JWT.decode(jwt_token).getExpiresAt("cos")
        }

        System.out.println(jwt_token);
        chain.doFilter(request,response);
    }
}
