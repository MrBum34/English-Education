package by.vstu.english_education.service;

import by.vstu.english_education.entity.TestResults;
import by.vstu.english_education.entity.User;
import by.vstu.english_education.repository.TestResultsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TestResultsService {

    @Autowired
    private TestResultsRepository testResultsRepository;

    public void saveTestResult(TestResults testResults) {
        testResultsRepository.save(testResults);
    }

    public List<TestResults> findByUserAndTestIn(User user, List<Long> testIds) {
        return testResultsRepository.findByUserAndTestIdIn(user, testIds);
    }
}
