package by.vstu.english_education.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "test")
public class Test extends AbstractEntity {
    private String task;
    private String type;
    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers;
    @ManyToOne()
    private Lesson lesson;
    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TestResults> testResults;
}