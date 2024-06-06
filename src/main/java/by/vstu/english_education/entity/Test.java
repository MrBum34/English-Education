package by.vstu.english_education.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "test")
public class Test extends AbstractEntity {
    private String task;
    private String type;
    @OneToMany(mappedBy = "test")
    private List<Answer> answers;
    @ManyToOne
    private Lesson lesson;
    @OneToMany(mappedBy = "test")
    private List<TestResults> testResults;
}