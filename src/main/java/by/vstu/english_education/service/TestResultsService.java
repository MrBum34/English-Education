package by.vstu.english_education.service;

import by.vstu.english_education.entity.Dto.TestResultsDto;
import by.vstu.english_education.entity.Lesson;
import by.vstu.english_education.entity.TestResults;
import by.vstu.english_education.entity.User;
import by.vstu.english_education.repository.TestResultsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TestResultsService {

    @Autowired
    private TestResultsRepository testResultsRepository;
    @Autowired
    private UserService userService;


    public List<TestResultsDto> getScoreByTests(String username) {
        List<TestResultsDto> testResultsDtos = new ArrayList<>();
        User user = userService.findByUsername(username);
        List<TestResults> tests = user.getTestResults();
        Map<Lesson, Map<Date, List<TestResults>>> groupedTests = tests.stream().collect(Collectors.groupingBy(testResult -> testResult.getTest().getLesson(), Collectors.groupingBy(TestResults::getDateTime)));
        for (Map.Entry<Lesson, Map<Date, List<TestResults>>> lessonEntry : groupedTests.entrySet()) {
            Lesson lesson = lessonEntry.getKey();
            Map<Date, List<TestResults>> dateGroups = lessonEntry.getValue();
            for (Map.Entry<Date, List<TestResults>> dateEntry : dateGroups.entrySet()) {
                Date date = dateEntry.getKey();
                List<TestResults> testResultsForDate = dateEntry.getValue();
                long correctCount = testResultsForDate.stream().filter(TestResults::isCorrect).count();
                long totalCount = testResultsForDate.size();
                double score = (correctCount / (double) totalCount) * 100;
                TestResultsDto dto = new TestResultsDto(score, date, lesson, user);
                testResultsDtos.add(dto);
            }
        }
        return testResultsDtos;
    }

    public List<TestResultsDto> getTestResultsByLesson(Long lessonId) {
        List<TestResults> testResults = testResultsRepository.findByTestLessonId(lessonId);
        Map<User, Map<Date, List<TestResults>>> groupedByUserAndDate = testResults.stream().collect(Collectors.groupingBy(TestResults::getUser, Collectors.groupingBy(TestResults::getDateTime)));

        List<TestResultsDto> resultsDtos = new ArrayList<>();

        for (Map.Entry<User, Map<Date, List<TestResults>>> userEntry : groupedByUserAndDate.entrySet()) {
            User user = userEntry.getKey();
            Map<Date, List<TestResults>> dateGroups = userEntry.getValue();
            for (Map.Entry<Date, List<TestResults>> dateEntry : dateGroups.entrySet()) {
                Date date = dateEntry.getKey();
                List<TestResults> userTestResults = dateEntry.getValue();

                long correctCount = userTestResults.stream().filter(TestResults::isCorrect).count();
                long totalCount = userTestResults.size();
                double score = (correctCount / (double) totalCount) * 100;

                TestResultsDto dto = new TestResultsDto(score, date, userTestResults.get(0).getTest().getLesson(), user);
                resultsDtos.add(dto);
            }
        }
        return resultsDtos;
    }
}
