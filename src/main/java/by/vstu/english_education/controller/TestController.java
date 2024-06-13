package by.vstu.english_education.controller;

import by.vstu.english_education.entity.Answer;
import by.vstu.english_education.entity.Dto.TestWrapper;
import by.vstu.english_education.entity.Lesson;
import by.vstu.english_education.entity.Test;
import by.vstu.english_education.service.LessonService;
import by.vstu.english_education.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private LessonService lessonService;

    @PreAuthorize("hasRole('TEACHER')")
    @GetMapping("/lessons/{lessonId}/add-tests")
    public String showAddTestsForm(@PathVariable Long lessonId, Model model) {
        model.addAttribute("lessonId", lessonId);
        model.addAttribute("testWrapper", new TestWrapper());
        return "add-tests";
    }

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/add-tests")
    public String addTests(@RequestParam Long lessonId, @ModelAttribute("testWrapper") TestWrapper testWrapper) {
        Lesson lesson = lessonService.findById(lessonId);
        if (lesson != null) {
            for (Test test : testWrapper.getTests()) {
                test.setLesson(lesson);
                testService.saveTestWithAnswers(test);
            }
        }
        return "redirect:/lessons";
    }

    @GetMapping("/lessons/{lessonId}/test")
    public String showTestPage(@PathVariable Long lessonId, Model model) {
        List<Test> tests = testService.findByLessonId(lessonId);
        for (Test test : tests) {
            for (Answer answer : test.getAnswers()) {
                answer.setCorrect(false);
                answer.setNumber((byte) 0);
            }
        }
        TestWrapper testWrapper = new TestWrapper();
        testWrapper.setTests(tests);
        model.addAttribute("lessonId", lessonId);
        model.addAttribute("testWrapper", testWrapper);
        return "testing";

    }

    @PostMapping("/submitTest")
    public String submitTest(@RequestParam Long lessonId, @ModelAttribute("testWrapper") TestWrapper testWrapper, Authentication authentication, Model model) {
        Date date = new Date();
        double score = testService.checkTestResults(testWrapper, authentication.getName(), lessonId, date);
        model.addAttribute("score", score);
        return "result";
    }
}
