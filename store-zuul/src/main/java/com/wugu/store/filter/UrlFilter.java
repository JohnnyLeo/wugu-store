package com.wugu.store.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;


public class UrlFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    /**
     * if the url is session's address, it must to be stopped
     * @return whether need to use the filter
     */
    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        String url = requestContext.getRequest().getRequestURI();
        return url.contains("/session");
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        requestContext.setSendZuulResponse(false);
        requestContext.setResponseBody("非法请求！");
        requestContext.getResponse().setContentType("text/plain; charset=utf-8");
        return null;
    }
}
