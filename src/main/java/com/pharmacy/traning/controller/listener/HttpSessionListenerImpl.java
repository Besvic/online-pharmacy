package com.pharmacy.traning.controller.listener;


import com.pharmacy.traning.controller.helper.SessionAttribute;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.*;

@WebListener
public class HttpSessionListenerImpl implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        se.getSession().setAttribute(SessionAttribute.LOCALE, SessionAttribute.RU_RU);
    }


}
