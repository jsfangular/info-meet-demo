package com.volvo.demo.jsfangular.restadapter.configuration.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class RestCacheInterceptor implements HandlerInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(RestCacheInterceptor.class);

    public static final String CACHE_CONTROL = "Cache-Control";
    public static final String CACHE_PRAGMA = "Pragma";
    public static final String CACHE_EXPIRES = "Expires";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOG.debug("REST Cache Interceptor got request");

        response.setHeader(CACHE_CONTROL, "no-cache, no-store, must-revalidate");
        response.setHeader(CACHE_PRAGMA, "no-cache");
        response.setHeader(CACHE_EXPIRES, "0");

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
