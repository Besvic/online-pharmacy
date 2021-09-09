package com.pharmacy.traning.controller.filter;

import jakarta.servlet.*;

import java.io.IOException;

/*@WebFilter(filterName = "PageFilter", urlPatterns = {"*.jsp"})*/
public class PageFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {

    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
       /* HttpServletRequest httpRequest = (HttpServletRequest) request;
        String contextPath = httpRequest.getContextPath();
        int length = contextPath.length();
        String uri = httpRequest.getRequestURI();
        String path = uri.substring(length);
        httpRequest.getSession().setAttribute("current_page", path);
        chain.doFilter(request, response);*/
        // TODO: 08.09.2021 не работает, не уверен что он вообще нужен урок 30.08.21 около 1.30'
    }
}
