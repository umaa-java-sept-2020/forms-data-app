package com.java.filters;


import javax.servlet.*;
import java.io.IOException;

public class GatewayFilter implements Filter {
    private FilterConfig filterConfig;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        filterConfig.getServletContext().log("doFilter: "+this.getClass());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
