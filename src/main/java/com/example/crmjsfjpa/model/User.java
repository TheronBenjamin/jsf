package com.example.crmjsfjpa.model;

//import jakarta.persistence.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(
            nullable = false,
            length = 30
    )
    private String username;
    @Column(
            nullable = false
    )
    private String password;
    @Column(
            nullable = false
    )
    private String mail;
    @Column(
            nullable = false
    )
    @Enumerated(EnumType.STRING)
    private UserGrantsEnum grants;

    public User() {
    }

    public User(Integer id, String username, String password, String mail, UserGrantsEnum grants) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.grants = grants;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public UserGrantsEnum getGrants() {
        return grants;
    }

    public void setGrants(UserGrantsEnum grants) {
        this.grants = grants;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", mail='" + mail + '\'' +
                ", grants='" + grants + '\'' +
                '}';
    }
}
