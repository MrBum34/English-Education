package by.vstu.english_education.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lesson> lessons;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TestResults> testResults;

    public String getFullName() {
        return lastname + " " + firstname + " " + patronymic;
    }
}