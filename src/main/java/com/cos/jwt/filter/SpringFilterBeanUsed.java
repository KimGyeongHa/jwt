package com.cos.jwt.filter;

import javax.servlet.*;
import java.io.IOException;

public class SpringFilterBeanUsed implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Bean으로 등록한 필터사용");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
