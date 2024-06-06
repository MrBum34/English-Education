package by.vstu.english_education.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "theory")
public class Theory extends AbstractEntity {
    private String text;
    private int number;
    @ManyToOne
    private Lesson lesson;
}