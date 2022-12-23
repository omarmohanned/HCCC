package com.example.hccc;

public class retrieve_doctor_list {

    private String National;
    private String email;
    private String full_name;
    private String major;
    private String phone;
    private String uid;

    public retrieve_doctor_list() {
    }

    public retrieve_doctor_list(String national, String email, String full_name, String major, String phone, String uid) {
        this.National = national;
        this.email = email;
        this.full_name = full_name;
        this.major = major;
        this.phone = phone;
        this.uid = uid;
    }

    public String getNational() {
        return National;
    }

    public void setNational(String national) {
        National = national;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
