package by.vstu.english_education.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "test_results")
public class TestResults extends AbstractEntity {
    @ManyToOne(cascade = CascadeType.ALL)
    private Test test;
    @ManyToOne()
    private User user;
    private boolean correct;
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date dateTime;

}