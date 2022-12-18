package br.com.ada.georg.twitter;

public class User {

    //TODO: Implement email checking.
    private static Object[] registeredEmails = new Object[1];

    private String name;
    private String email;
    private String birthDate;

    public User(String name, String email, String birthDate) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthDate() {
        return birthDate;
    }
}
