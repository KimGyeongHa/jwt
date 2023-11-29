package com.cos.jwt.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cos.jwt.config.auth.PrincipleDetailsService;
import com.cos.jwt.model.User;
import com.cos.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

        String jwt_token = request.getHeader("jwt_token");

        if(jwt_token != null) {
            String username = JWT.require(Algorithm.HMAC512("cos")).build().verify(jwt_token.replace("barer ", "")).getClaim("name").asString();

            //Authentication authentication = new UsernamePasswordAuthenticationToken()


            //PrincipleDetailsService principleDetailsService = new PrincipleDetailsService()

        }
       chain.doFilter(request,response);
    }
}
