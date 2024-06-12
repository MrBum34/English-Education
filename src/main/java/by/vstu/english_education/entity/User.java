package by.vstu.english_education.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User extends AbstractEntity {
    @Column(unique = true)
    private String username;
    private String password;
    private String role;

    private String firstname;
    private String lastname;
    private String patronymic;
    @OneToMany(mappedBy = "author")
    private List<Lesson> lessons;
    @OneToMany(mappedBy = "user")
    private List<TestResults> testResults;
}