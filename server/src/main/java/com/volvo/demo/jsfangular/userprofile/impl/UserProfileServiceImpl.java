package com.volvo.demo.jsfangular.userprofile.impl;

import javax.inject.Named;

import com.volvo.demo.jsfangular.userprofile.UserProfile;
import com.volvo.demo.jsfangular.userprofile.UserProfileService;

@Named(UserProfileService.SERVICE_NAME)
public class UserProfileServiceImpl implements UserProfileService {

    @Override
    public UserProfile getUserProfile() {
        return null;
    }
}
