package by.vstu.english_education.repository;

import by.vstu.english_education.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {
}