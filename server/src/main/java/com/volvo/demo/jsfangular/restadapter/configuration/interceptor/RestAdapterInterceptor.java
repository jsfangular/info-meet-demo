package com.volvo.demo.jsfangular.restadapter.configuration.interceptor;

import static com.volvo.demo.jsfangular.restadapter.configuration.constants.RestAdapterConstants.JSF_FACES_CONTEXT;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class RestAdapterInterceptor implements HandlerInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(RestAdapterInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOG.debug("JSF REST Adapter got request");
        FacesContext facesContext = (FacesContext) request.getAttribute(JSF_FACES_CONTEXT);

        if (facesContext == null) {
            throw new IllegalStateException("Request is dropped as there is no FacesContext!");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
