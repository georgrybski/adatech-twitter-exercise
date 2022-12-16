package br.com.ada.georg.twitter;

public class User {
    private String name;
    private String email;
    private String birthDate;

    public User(String name, String email, String birthDate) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthDate='" + birthDate + '\'' +
                '}';
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
