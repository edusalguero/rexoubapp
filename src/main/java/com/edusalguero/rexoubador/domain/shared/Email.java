package com.edusalguero.rexoubador.domain.shared;


public class Email implements ValueObject<Email> {
    private String email;

    public Email(String email) {
        this.email = email;
    }

    public String email() {
        return email;
    }

    @Override
    public String toString() {
        return "Email{" +
                "email='" + email + '\'' +
                '}';
    }

    public int hashCode() {
        return email.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Email other = (Email) o;

        return sameValueAs(other);
    }

    @Override
    public boolean sameValueAs(Email other) {
        return other != null && this.email.equals(other.email);
    }
}
