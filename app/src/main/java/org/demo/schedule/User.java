package org.demo.schedule;

public class User {
    private String FirstName;
    private String LastName;
    private String PhoneNumber;
    private String type;

    public User(String name, String surname, String phoneNumber, String type) {
        FirstName = name;
        LastName = surname;
        PhoneNumber = phoneNumber;
        this.type = type;
    }
}
