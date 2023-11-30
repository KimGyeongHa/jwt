package com.cos.jwt.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cos.jwt.config.auth.PrincipleDetailsService;
import com.cos.jwt.model.User;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("로그인시도");
        try{
         /*
            BufferedReader br = request.getReader();
            String input = null;
            if((input = br.readLine()) != null)
                System.out.println(input);
         */

            ObjectMapper om = new ObjectMapper();
            Authentication authentication = null;
            om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); //객체에 존재하지 않는값 무시
            User user = om.readValue(request.getInputStream(), User.class);

            UsernamePasswordAuthenticationToken token  = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());

            authentication = authenticationManager.authenticate(token);
            PrincipleDetailsService principal = (PrincipleDetailsService) authentication.getPrincipal();

            return authentication;

        }catch (Exception e){
           e.printStackTrace();
        }

        return null;
    }

    // attemptAuthentication 실행 후 인증성공 시 실행되는 메서드
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        PrincipleDetailsService principal = (PrincipleDetailsService)authResult.getPrincipal();

        String jwt_token = JWT.create()
        .withSubject("cos토큰")
        .withExpiresAt(new Date(System.currentTimeMillis() + (60000*10)))
        .withClaim("name",principal.getUser().getUsername())
        .withClaim("id",principal.getUser().getId())
        .sign(Algorithm.HMAC512("cos"));

        response.addHeader("jwt_token","barer " + jwt_token);
    }
}
