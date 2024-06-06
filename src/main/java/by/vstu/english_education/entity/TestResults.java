package by.vstu.english_education.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "test_results")
public class TestResults extends AbstractEntity {
    @ManyToOne
    private Test test;
    @ManyToOne
    private User user;
    private boolean correct;
}