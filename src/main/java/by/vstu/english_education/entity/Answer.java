package by.vstu.english_education.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "answer")
public class Answer extends AbstractEntity {
    @ManyToOne
    private Test test;
    private String answer;
    private boolean correct;
    private byte number;
}