package com.pharmacy.traning.controller.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.*;

import static com.pharmacy.traning.controller.command.SessionAttribute.*;

/**
 * The type Http session listener.
 */
@WebListener
public class HttpSessionListenerImpl implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        String currentLocale = String.valueOf(session.getAttribute(LOCALE));
        session.setAttribute(LOCALE, currentLocale.isEmpty() ? currentLocale : RU_RU);
    }
}
