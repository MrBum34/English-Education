package by.vstu.english_education.repository;

import by.vstu.english_education.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    public List<Lesson> findByAuthorUsername(String username);

    public Optional<Lesson> findById(Long id);
}