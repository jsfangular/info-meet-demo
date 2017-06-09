package com.volvo.demo.jsfangular.restadapter.configuration.filter;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;

import com.volvo.demo.jsfangular.restadapter.configuration.constants.RestAdapterConstants;

/**
 * Below configuration is only for a purpose of demo solution!
 */
public class CORSFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(CORSFilter.class);

    private static final String CORS_ORIGIN = "Access-Control-Allow-Origin";
    private static final String CORS_HEADERS = "Access-Control-Allow-Headers";
    private static final String CORS_METHODS = "Access-Control-Allow-Methods";
    private static final String CORS_CREDENTIALS = "Access-Control-Allow-Credentials";

    private String allowedMethods;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.allowedMethods = getAllowedMethods();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOG.debug("REST CORS Filter is on - got the request");

        HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;

        res.setHeader(CORS_ORIGIN, createDevelopmentOrigin(req));
        res.setHeader(CORS_HEADERS, "Content-Type");
        res.setHeader(CORS_METHODS, allowedMethods);
        res.setHeader(CORS_CREDENTIALS, Boolean.TRUE.toString());

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    String createDevelopmentOrigin(HttpServletRequest request) {
        try {
            return new URL(request.getScheme(), request.getServerName(), RestAdapterConstants.ANGULAR_CLIENT_PORT_LOCAL, "").toString();
        } catch (MalformedURLException e) {
            LOG.error("There was problem when trying to construct local development origin for webclient" + e.getMessage());
            return "";
        }
    }

    String getAllowedMethods() {
        HttpMethod methods[] = { HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.OPTIONS };

        List<String> methodNames = new ArrayList<>();
        for (HttpMethod method : methods) {
            methodNames.add(method.toString());
        }
        return String.join(", ", methodNames);
    }
}
