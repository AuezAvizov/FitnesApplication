package com.example.fitnesapplication.Yoga;

public class User {
    private String fio;
    private String username;
    private String email;
    private String contactPhone;
    private String address;
    private String country;
    private String bin;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String fio, String username, String email, String contactPhone, String address, String country, String bin) {
        this.fio = fio;
        this.username = username;
        this.email = email;
        this.contactPhone = contactPhone;
        this.address = address;
        this.country = country;
        this.bin = bin;
    }

    // Getters and setters
    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }
}
