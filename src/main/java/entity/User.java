package entity;

import enums.RoleType;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class User implements Serializable{
    @Id
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Person person;

    @Column
    @Enumerated(value = EnumType.STRING)
    private RoleType role;

    @Column
    private String email;

    @Column
    private String phoneNumber;

    @Column
    private String password;

    public User(Person person, RoleType role, String email, String phoneNumber, String password) {
        this.person = person;
        this.role = role;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public User(Person person, String email) {
        this.email = email;
        this.person = person;
        this.role = RoleType.CITIZEN;
    }

    public User() {
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
