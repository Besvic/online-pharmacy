package com.pharmacy.traning.controller.listener;

import com.pharmacy.traning.model.pool.ConnectionPool;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ServletContextListenerImpl implements ServletContextListener {
    // TODO: 08.09.2021 кидает странные ошибки при открытии конекшенов, а так же куча предупреждений при их закрытии
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConnectionPool.getInstance();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getInstance().destroyPool();
    }
}
