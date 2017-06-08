package com.volvo.demo.jsfangular.restadapter.configuration.resolver;

import javax.faces.context.FacesContext;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public interface FacesContextArgumentResolver extends HandlerMethodArgumentResolver {

    @Override
    FacesContext resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory)
            throws Exception;

}
