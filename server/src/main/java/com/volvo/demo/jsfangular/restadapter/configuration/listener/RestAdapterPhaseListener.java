package com.volvo.demo.jsfangular.restadapter.configuration.listener;

import static com.volvo.demo.jsfangular.restadapter.configuration.constants.RestAdapterConstants.JSF_FACES_CONTEXT;
import static com.volvo.demo.jsfangular.restadapter.configuration.constants.RestAdapterConstants.REST_JSF_ADAPTER_CONTEXT;
import static com.volvo.demo.jsfangular.restadapter.configuration.constants.RestAdapterConstants.REST_SPRING_ADAPTER_CONTEXT;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;;

public class RestAdapterPhaseListener implements PhaseListener {
    private static final long serialVersionUID = 1707445743951984539L;
    private static final Logger LOG = LoggerFactory.getLogger(RestAdapterPhaseListener.class);

    @Override
    public void beforePhase(PhaseEvent event) {
    }

    @Override
    public void afterPhase(PhaseEvent event) {
        if (restAdapterShouldBeInvoked()) {
            HttpServletRequest request = getRequest();
            HttpServletResponse response = getResponse();

            addFacesContextToRequest(request);
            dispatchToRestServlet(request, response);

            getFacesContext().responseComplete();
        }
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

    boolean restAdapterShouldBeInvoked() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        return isRestAdapterRequest(request.getRequestURI());
    }

    void addFacesContextToRequest(HttpServletRequest request) {
        request.setAttribute(JSF_FACES_CONTEXT, getFacesContext());
    }

    void dispatchToRestServlet(HttpServletRequest request, HttpServletResponse response) {
        String requestPath = request.getRequestURI();
        String requestQueryParameters = request.getQueryString();

        try {
            String redispatch = createRESTRequest(requestPath, requestQueryParameters);
            LOG.warn("Dispatching to: " + redispatch);
            request.getRequestDispatcher(redispatch).forward(request, response);
        } catch (ServletException | IOException e) {
            LOG.error("Something goes wrong when trying to dispatch to Spring REST MVC adapter :(" + e.getMessage());
        }
    }

    boolean isRestAdapterRequest(String path) {
        return !StringUtils.isEmpty(path) && path.contains(REST_JSF_ADAPTER_CONTEXT);
    }

    String createRESTRequest(String originalRequestPath, String queryParameters) {
        int restIndex = originalRequestPath.indexOf(REST_JSF_ADAPTER_CONTEXT) + REST_JSF_ADAPTER_CONTEXT.length();
        String requestPath = originalRequestPath.substring(restIndex);
        if (StringUtils.isEmpty(requestPath)) {
            requestPath += "/";
        }
        if (!StringUtils.isEmpty(queryParameters)) {
            requestPath += "?" + queryParameters;
        }
        return REST_SPRING_ADAPTER_CONTEXT + requestPath;
    }

    FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    HttpServletRequest getRequest() {
        return (HttpServletRequest) getFacesContext().getExternalContext().getRequest();
    }

    HttpServletResponse getResponse() {
        return (HttpServletResponse) getFacesContext().getExternalContext().getResponse();
    }
}
