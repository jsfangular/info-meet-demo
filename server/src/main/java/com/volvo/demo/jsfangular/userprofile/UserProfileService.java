package com.volvo.demo.jsfangular.userprofile;

public interface UserProfileService {

    String SERVICE_NAME = "UserProfileService";

    String SERVICE_EL_NAME = "#{" + SERVICE_NAME + "}";

    UserProfile getUserProfile();
}
