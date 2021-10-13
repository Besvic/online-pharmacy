package com.pharmacy.traning.controller.filter;

import com.pharmacy.traning.controller.command.SessionAttribute;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * The type Current page filter.
 */
@WebFilter(filterName = "CurrentPageFilter", urlPatterns = {"/*"})
public class CurrentPageFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        String currentPage = httpRequest.getRequestURL().toString();

        if (currentPage.contains("pages/")) {
            int index = currentPage.indexOf("pages/");
            currentPage = currentPage.substring(index);
            session.setAttribute(SessionAttribute.CURRENT_PAGE, currentPage);

        } else if (currentPage.contains("controller") && !httpRequest.getParameterMap().isEmpty()
               && httpRequest.getQueryString() != null && !httpRequest.getQueryString().contains("command=change_locale")) {
            int index = currentPage.indexOf("controller");
            currentPage = currentPage.substring(index) + "?" + httpRequest.getQueryString();
            session.setAttribute(SessionAttribute.CURRENT_PAGE, currentPage);
        }
        chain.doFilter(httpRequest, response);
    }
}
