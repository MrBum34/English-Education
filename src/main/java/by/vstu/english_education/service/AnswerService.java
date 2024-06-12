package by.vstu.english_education.service;

import by.vstu.english_education.entity.Answer;
import by.vstu.english_education.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    public void save(Answer answer) {
        answerRepository.save(answer);
    }
}
