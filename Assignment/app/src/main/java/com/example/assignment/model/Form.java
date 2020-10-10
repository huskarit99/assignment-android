package com.example.assignment.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Form implements Serializable {
    String userName, password,  birthdate, gender,  hobbies;

    public Form(){
    }

    public Form(String userName, String password, String birthdate, String gender, ArrayList<String> hobbies){
        this.userName = userName;
        this.password = "";
        for (int i = 0; i < password.length(); i++)
            this.password += "*";
        this.birthdate = birthdate;
        this.gender = gender;
        this.hobbies = "";
        for (int i = 0; i < hobbies.size(); i++){
            this.hobbies += hobbies.get(i);
            if (i != hobbies.size() - 1){
                this.hobbies += ", ";
            }
        }
    }

    public String getHobbies() {
        return hobbies;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getGender() {
        return gender;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
