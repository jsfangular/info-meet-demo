package com.volvo.demo.jsfangular.faces.userprofile;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.volvo.demo.jsfangular.userprofile.UserProfile;

@SessionScoped
@ManagedBean(name = UserProfileBean.BEAN_NAME)
public class UserProfileBean {

    public static final String BEAN_NAME = "userProfileBean";
    public static final String BEAN_EL_NAME = "#{" + BEAN_NAME + "}";

    private UserProfile currentProfile;

    @PostConstruct
    public void init() {
        this.currentProfile = new UserProfile();
    }

    public void createUserProfile(UserProfile profile) {
        this.currentProfile = profile;
    }

    public void clearUserProfile() {
        this.currentProfile = null;
    }

    public UserProfile getCurrentProfile() {
        return currentProfile;
    }
}
