package org.demo.schedule;

public class User {
    private String FirstName;
    private String LastName;
    private String PhoneNumber;
    private String Type;
    private String Parent;

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getType() {
        return Type;
    }

    public String getParent() {
        return Parent;
    }

    public User(String firstName, String lastName, String phoneNumber, String type) {
        FirstName = firstName;
        LastName = lastName;
        PhoneNumber = phoneNumber;
        Type = type;
    }

    public User(String firstName, String lastName, String phoneNumber, String type, String parent) {
        FirstName = firstName;
        LastName = lastName;
        PhoneNumber = phoneNumber;
        Type = type;
        Parent = parent;
    }
}
