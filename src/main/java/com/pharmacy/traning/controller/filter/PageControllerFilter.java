/*
package com.pharmacy.traning.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

*/
/**
 * The type Page controller filter.
 *//*

@WebFilter(urlPatterns = "/controller")
public class PageControllerFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        */
/*HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String currentPage = httpServletRequest.getRequestURI();

        int indexStart = currentPage.indexOf("/controller");

        String command = currentPage.substring(indexStart);

        System.out.println(httpServletRequest.getContextPath() + command );
        httpServletRequest.getRequestDispatcher(httpServletRequest.getContextPath() + command).forward(request, response);

        chain.doFilter(request, response);*//*

        chain.doFilter(request, response);
    }
}
*/
