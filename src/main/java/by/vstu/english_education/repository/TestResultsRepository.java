package by.vstu.english_education.repository;

import by.vstu.english_education.entity.TestResults;
import by.vstu.english_education.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestResultsRepository extends JpaRepository<TestResults, Long> {
    List<TestResults> findByUserAndTestIdIn(User user, List<Long> testIds);
}