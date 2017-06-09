package com.volvo.demo.jsfangular.faces.userprofile;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.volvo.demo.jsfangular.userprofile.UserProfile;

@ViewScoped
@ManagedBean(name = RegistrationBean.BEAN_NAME)
public class RegistrationBean implements Serializable {

    private static final long serialVersionUID = 8023060752939125890L;

    public static final String BEAN_NAME = "registrationBean";

    public static final String BEAN_EL_NAME = "#{" + BEAN_NAME + "}";

    @ManagedProperty(value = UserProfileBean.BEAN_EL_NAME)
    private UserProfileBean userSession;

    private String login;

    @PostConstruct
    public void init() {
    }

    public void createUserProfile() {
        this.userSession.createUserProfile(new UserProfile(this.login, getCurrentSessionId()));
    }

    public void clearUserProfile() {
        this.userSession.clearUserProfile();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public UserProfileBean getUserSession() {
        return userSession;
    }

    public void setUserSession(UserProfileBean userSession) {
        this.userSession = userSession;
    }

    String getCurrentSessionId() {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionId(false);
    }

}
