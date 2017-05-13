package com.edusalguero.rexoubador.application.user;

import com.edusalguero.rexoubador.domain.model.user.UserId;

@SuppressWarnings("WeakerAccess")
public class UserUpdateRequest {
    private UserId userId;
    private String password = null;
    private String firstName = null;
    private String lastName = null;

    public UserUpdateRequest(UserId userId) {
        this.userId = userId;
    }

    public UserUpdateRequest(UserId userId, String password, String firstName, String lastName) {
        this.userId = userId;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserUpdateRequest(UserId userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public UserUpdateRequest(UserId userId, String firstName, String lastName) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void password(String password) {
        this.password = password;
    }

    public void firstName(String firstName) {
        this.firstName = firstName;
    }

    public void lastName(String lastName) {
        this.lastName = lastName;
    }

    public UserId getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean passwordHasChanged() {
        return password != null;
    }

    public boolean nameHasChanged() {
        return firstName != null || lastName != null;
    }
}
