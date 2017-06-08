package com.volvo.demo.jsfangular.restadapter.configuration.resolver;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.volvo.demo.jsfangular.restadapter.configuration.constants.RestAdapterConstants;

public class FacesContextMethodArgumentResolver implements FacesContextArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return supportsParameterType(parameter.getParameterType());
    }

    @Override
    public FacesContext resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) throws Exception {

        if (webRequest != null && webRequest.getClass().isAssignableFrom(ServletWebRequest.class)) {
            ServletWebRequest request = (ServletWebRequest) webRequest;
            HttpServletRequest req = request.getRequest();
            return (FacesContext) req.getAttribute(RestAdapterConstants.JSF_FACES_CONTEXT);
        }
        return null;
    }

    boolean supportsParameterType(Class<?> parameterType) {
        return FacesContext.class.equals(parameterType);
    }
}
