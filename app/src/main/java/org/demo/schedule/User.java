package org.demo.schedule;

public class User {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String type;
    private String parent;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getType() {
        return type;
    }

    public String getParent() { return parent; }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }


    public User(String firstName, String lastName, String phoneNumber, String type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.type = type;
    }

    public User(String firstName, String lastName, String phoneNumber, String type, String parent) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.type = type;
        this.parent = parent;
    }

    public User(){

    }
}
