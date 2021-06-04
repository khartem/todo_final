package com.example.demo.SERVER.tables;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    public User(String login, String surname, String name, String father_name, String password, LocalDate birthday) {
        this.login = login;
        this.surname = surname;
        this.name = name;
        this.father_name = father_name;
        this.password = password;
        this.birthday = birthday;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String login;

    @Column
    private String surname;

    @Column
    private String name;

    @Column
    private String father_name;

    @Column
    private String password;

    @Column
    private LocalDate birthday;

    @CreatedDate
    @Column
    private LocalDate created;

    @LastModifiedDate
    @Column
    private LocalDate updated;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", father_name='" + father_name + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}