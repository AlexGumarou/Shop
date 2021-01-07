package entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table (name = "users")
public class PersonalData {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Empty login")
    @Size(min = 3, max = 20, message = "Name should have min 3 and max 20 letters")
    private String login;
    @NotEmpty(message = "Empty pass")
    @Size(min = 3, max = 20, message = "Pass should have min 3 and max 20 letters")
    private String pass;

    private String name;

    private String surname;
    private String address;
    @Email(message = "This is not correct email")
    private String email;
    private String phone;

    public PersonalData() {
    }

    public PersonalData(String login, String pass, String name, String surname) {
        this.login = login;
        this.pass = pass;
        this.name = name;
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return  "login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'';
    }
}
