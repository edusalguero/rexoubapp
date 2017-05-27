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
    private int harvestersCount;
    private int observersCount;

    public UserInformationResponse(User user) {
        this.id = user.id();
        this.username = user.username();
        this.firstName = user.firstName();
        this.lastName = user.lastName();
        this.signUpDate = user.signUpDate();
        this.serverCount = user.servers().size();
        this.observersCount = user.observers().size();
        this.harvestersCount = user.harvesters().size();
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

    public int getServerCount() {
        return serverCount;
    }

    public int getHarvestersCount() {
        return harvestersCount;
    }

    public int getObserversCount() {
        return observersCount;
    }

    public String getSignUpDate() {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
        df.setTimeZone(tz);
        return df.format(signUpDate);
    }
}
