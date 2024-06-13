package by.vstu.english_education.entity.Dto;

import by.vstu.english_education.entity.Lesson;
import by.vstu.english_education.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class TestResultsDto {
    public double score;
    public Date date;
    public Lesson lesson;
    public User user;
}
