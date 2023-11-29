package com.cos.jwt.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse)servletResponse;

        if(req.getMethod().equals("POST")) {
            System.out.println("post요청됨");
            String header = req.getHeader("Authorization");

            if(header.equals("token")) filterChain.doFilter(req,res);
            else System.out.println("filter3 토큰고장");
        }
    }
}
