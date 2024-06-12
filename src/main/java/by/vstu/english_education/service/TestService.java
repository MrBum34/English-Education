package by.vstu.english_education.service;

import by.vstu.english_education.entity.Lesson;

import by.vstu.english_education.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import by.vstu.english_education.entity.Test;
import by.vstu.english_education.entity.Answer;

import java.util.List;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private AnswerService answerService;

    public void saveTestWithAnswers(Test test) {
        Test savedTest = testRepository.save(test);
        for (Answer answer : test.getAnswers()) {
            answer.setTest(savedTest);
            answerService.save(answer);
        }
    }
    public List<Test> findByLessonId(Long id){
        return testRepository.findByLessonId(id);
    }

    public Test findById(Long testId) {
        return testRepository.findById(testId).orElse(null);
    }
    public void updateTest(Test test) {
        testRepository.save(test);
    }
}