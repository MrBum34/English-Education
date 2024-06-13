package by.vstu.english_education.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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