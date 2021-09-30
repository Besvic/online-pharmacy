package com.pharmacy.traning.controller.filter;

import com.pharmacy.traning.controller.comand.PathToPage;
import com.pharmacy.traning.model.entity.Position;
import com.pharmacy.traning.model.entity.User;
import com.pharmacy.traning.model.entity.UserStatus;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static com.pharmacy.traning.controller.comand.RequestAttribute.ERROR;
import static com.pharmacy.traning.controller.comand.SessionAttribute.ADMIN;
import static com.pharmacy.traning.controller.comand.SessionAttribute.USER;

@WebFilter(urlPatterns = {"/pages/user/*", "/pages/admin/*"},
    initParams = {@WebInitParam(name = "SIGN_IN_PATH", value = "/pages/enter/sign_in.jsp")})
public class PageRedirectSecurityFilter implements Filter {

    private String signInPath;
    private final String USER_PATH = "/pages/user/";
    private final String ADMIN_PATH = "/pages/admin/";

    public void init(FilterConfig config) throws ServletException {
        signInPath = config.getInitParameter("SIGN_IN_PATH");
    }

    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
        String currentPage = httpServletRequest.getRequestURI();
       /* System.out.println(currentPage);
        try {
            Optional<User> userOptional = Optional.ofNullable((User) session.getAttribute(USER));
            Position userPosition = userOptional.isPresent() ? userOptional.get().getPosition() : null;
            if (userOptional.isPresent() && (userPosition.getValue().equalsIgnoreCase(ADMIN) && currentPage.contains(ADMIN_PATH) ||
                    userPosition.getValue().equalsIgnoreCase(USER) && currentPage.contains(USER_PATH))){
               // System.out.println("Context path: " + httpServletRequest.getContextPath());

            }else{
                System.out.println("Context path: " + httpServletRequest.getContextPath());
                httpServletRequest.getRequestDispatcher(signInPath).forward(httpServletRequest, httpServletResponse);
                //httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + signInPath);
            }
        } catch (IOException | NullPointerException e) {
            httpServletRequest.setAttribute(ERROR, e);
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + PathToPage.ERROR_404);
        }*/
   //     httpServletRequest.getRequestDispatcher(signInPath).forward(httpServletRequest, httpServletResponse);
      /*  if (currentPage.contains("/controller")) {
            int indexStart = currentPage.indexOf("/controller");

            String command = currentPage.substring(indexStart);

            System.out.println(httpServletRequest.getContextPath() + command);
            httpServletRequest.getRequestDispatcher(httpServletRequest.getContextPath() + command).forward(request, response);
        }
        else */
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + signInPath);
        //httpServletRequest.getRequestDispatcher(signInPath).forward(httpServletRequest, httpServletResponse);
        chain.doFilter(request, response);
    }

}
