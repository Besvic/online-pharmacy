package com.pharmacy.traning.controller.comand;

public class Router {

    public enum RouterType{
        FORWARD,
        REDIRECT
    }
    private String pagePath;
    private RouterType routerType = RouterType.FORWARD;

    public Router(String pagePath, RouterType routerType) {
        this.pagePath = pagePath;
        this.routerType = routerType;
    }

    public String getPagePath() {
        return pagePath;
    }

    public RouterType getRouterType() {
        return routerType;
    }
}
