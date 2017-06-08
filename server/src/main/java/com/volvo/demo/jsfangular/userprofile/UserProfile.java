package com.volvo.demo.jsfangular.userprofile;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UserProfile implements Serializable {

    private static final long serialVersionUID = -867555269195620849L;

    private String login;
    private String name;
    private String surname;
    private String created;
    private String sessionId;

    public UserProfile() {
    }

    public UserProfile(String login, String sessionId) {
        this.login = login;
        this.name = "info";
        this.surname = "MEET";
        this.sessionId = sessionId;
        this.created = LocalDateTime.now().toString();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCreated() {
        return created;
    }

    public String getSessionId() {
        return sessionId;
    }
}
