package com.edusalguero.rexoubador.application.user;


import com.edusalguero.rexoubador.domain.model.user.User;
import com.edusalguero.rexoubador.domain.model.user.UserId;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class UserInformationResponse {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private Date signUpDate;
    private int serverCount;

    public UserInformationResponse(UserId id, String username, String firstName, String lastName, Date signUpDate, int serverCount) {
        this.id = id.toString();
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.signUpDate = signUpDate;
        this.serverCount = serverCount;
    }

    public UserInformationResponse(User user, int serverCount) {
        this.id = user.id();
        this.username = user.username();
        this.firstName = user.firstName();
        this.lastName = user.lastName();
        this.signUpDate = user.signUpDate();
        this.serverCount = serverCount;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSignUpDate() {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
        df.setTimeZone(tz);
        return df.format(signUpDate);
    }

    public int getServerCount() {
        return serverCount;
    }
}
