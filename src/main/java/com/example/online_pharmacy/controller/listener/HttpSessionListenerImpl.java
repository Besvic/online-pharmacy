package com.example.online_pharmacy.controller.listener;


import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.*;

import static com.example.online_pharmacy.controller.comand.SessionAttribute.*;

@WebListener
public class HttpSessionListenerImpl implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        se.getSession().setAttribute(LOCALE, RU_RU);
    }


}
