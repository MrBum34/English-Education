package by.vstu.english_education.repository;

import by.vstu.english_education.entity.TestResults;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestResultsRepository extends JpaRepository<TestResults, Long> {
}