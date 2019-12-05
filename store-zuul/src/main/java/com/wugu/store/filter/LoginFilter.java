package com.wugu.store.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import java.io.IOException;

public class LoginFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 3;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        String url = requestContext.getRequest().getRequestURI();
        if (url.contains("/shoppingCart") || url.contains("/order")) {
            return requestContext.getRequest().getCookies() == null;
        }
        return false;
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        requestContext.setSendZuulResponse(false);
        try {
            requestContext.getResponse().sendRedirect("http://localhost:80/loginRegister/login.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
