package com.java.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class FormsFilter implements Filter {

    private FilterConfig filterConfig;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        filterConfig.getServletContext().log("doFilter: "+this.getClass());
        // pass query param as : ?x=20&y=200&x=230
        // try for httpServletRequest.getParameterXXX() in debug mode
        HttpServletRequest httpServletRequest = ((HttpServletRequest)request);
        String value = httpServletRequest.getParameter("x");
        filterConfig.getServletContext().log("filter: "+value);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
