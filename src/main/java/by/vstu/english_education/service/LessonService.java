package by.vstu.english_education.service;

import by.vstu.english_education.entity.Lesson;
import by.vstu.english_education.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {
    @Autowired
    private LessonRepository lessonRepository;

    public void saveLesson(Lesson lesson) {
        lessonRepository.save(lesson);
    }

    public List<Lesson> findLessonByAuthorUsername(String author) {
        return lessonRepository.findByAuthorUsername(author);
    }

    public Lesson getLessonById(Long id) {
        return lessonRepository.findById(id).orElse(null);
    }

    public void deleteLesson(Long id, String username) {
        Lesson lesson = lessonRepository.findById(id).orElse(null);
        if (lesson != null && lesson.getAuthor().getUsername().equals(username)) {
            lessonRepository.deleteById(id);
        }
    }

    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    public Lesson findById(Long id) {
        return lessonRepository.findById(id).orElse(null);
    }

    public void edit(Lesson newLesson) {
        Lesson lesson = lessonRepository.findById(newLesson.getId()).get();
        lesson.setTheory(newLesson.getTheory());
        lesson.setTitle(newLesson.getTitle());
        lessonRepository.save(lesson);
    }
}
