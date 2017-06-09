package com.volvo.demo.jsfangular.restadapter.api.userprofile;

import javax.faces.context.FacesContext;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.volvo.demo.jsfangular.faces.userprofile.UserProfileBean;
import com.volvo.demo.jsfangular.userprofile.UserProfile;

@Controller
@RequestMapping("/user-profile")
public class UserProfileController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<UserProfile> getUserProfile(FacesContext facesContext) {
        UserProfileBean userProfileBean = getUserProfileBean(facesContext);

        return new ResponseEntity<>(userProfileBean.getCurrentProfile(), HttpStatus.OK);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity<UserProfile> logOutUserProfile(FacesContext facesContext) {
        getUserProfileBean(facesContext).clearUserProfile();

        return new ResponseEntity<>(HttpStatus.OK);
    }

    UserProfileBean getUserProfileBean(FacesContext context) {
        return context.getApplication().evaluateExpressionGet(context, UserProfileBean.BEAN_EL_NAME, UserProfileBean.class);
    }
}
