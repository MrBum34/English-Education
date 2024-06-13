package by.vstu.english_education.service;

import by.vstu.english_education.entity.Answer;
import by.vstu.english_education.entity.Dto.TestWrapper;
import by.vstu.english_education.entity.Test;
import by.vstu.english_education.entity.TestResults;
import by.vstu.english_education.entity.User;
import by.vstu.english_education.repository.TestRepository;
import by.vstu.english_education.repository.TestResultsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class TestService {
    @Autowired
    private UserService userService;

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private AnswerService answerService;
    @Autowired
    private TestResultsRepository testResultsRepository;

    public void saveTestWithAnswers(Test test) {
        Test savedTest = testRepository.save(test);
        for (Answer answer : test.getAnswers()) {
            answer.setTest(savedTest);
            answerService.save(answer);
        }
    }

    public List<Test> findByLessonIdToTesting(Long id) {
        List<Test> tests = testRepository.findByLessonId(id);
        for (Test test : tests) {
            Collections.shuffle(test.getAnswers());
            for (Answer answer : test.getAnswers()) {
                answer.setCorrect(false);
                answer.setNumber((byte) 0);
            }
        }
        return tests;
    }

    public double checkTestResults(TestWrapper testWrapper, String username, Long lessonID, Date date) {
        User user = userService.findByUsername(username);
        List<Test> tests = testRepository.findByLessonId(lessonID);
        List<Test> testsUser = testWrapper.getTests();
        double count = 0;
        for (Test test : tests) {
            boolean correct = true;
            for (Test testUser : testsUser) {

                if (test.getId().equals(testUser.getId())) {
                    if (test.getType().equals("check")) {
                        for (Answer answer : test.getAnswers()) {
                            for (Answer answerUser : testUser.getAnswers()) {
                                if (answer.getId().equals(answerUser.getId())) {
                                    if (answer.isCorrect() != answerUser.isCorrect()) {
                                        correct = false;
                                    }
                                }
                            }
                        }

                    } else {
                        String constructTextCorrect = "";
                        String constructTextUser = "";
                        int maxAnswerTest = 0;
                        int maxAnswerUser = 0;
                        for (Answer answer : test.getAnswers()) {
                            if (answer.getNumber() > maxAnswerTest) {
                                maxAnswerTest = answer.getNumber();
                            }
                        }
                        for (Answer answer : testUser.getAnswers()) {
                            if (answer.getNumber() > maxAnswerUser) {
                                maxAnswerUser = answer.getNumber();
                            }
                        }
                        for (int i = 0; i < maxAnswerTest; i++) {
                            for (Answer answer : test.getAnswers()) {
                                if (answer.getNumber() == i) {
                                    constructTextCorrect = constructTextCorrect + answer.getAnswer();
                                }
                            }
                        }
                        for (int i = 0; i < maxAnswerUser; i++) {
                            for (Answer answer : testUser.getAnswers()) {
                                for (Answer answerTest : test.getAnswers()) {
                                    if (answer.getId().equals(answerTest.getId()) && answer.getNumber() == i) {
                                        constructTextUser = constructTextUser + answerTest.getAnswer();
                                    }
                                }
                            }
                        }
                        if (!constructTextCorrect.equals(constructTextUser)) {
                            correct = false;
                        }
                    }
                }
            }
            TestResults testResults = new TestResults();
            testResults.setDateTime(date);
            testResults.setTest(test);
            testResults.setUser(user);
            testResults.setCorrect(correct);
            testResultsRepository.save(testResults);
            if (correct) {
                count = count + 1;
            }
        }
        return ((double) 100 / tests.size()) * count;
    }
}