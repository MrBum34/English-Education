package by.vstu.english_education.controller;

import by.vstu.english_education.entity.Dto.TestWrapper;
import by.vstu.english_education.entity.Lesson;
import by.vstu.english_education.entity.Test;
import by.vstu.english_education.service.LessonService;
import by.vstu.english_education.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import by.vstu.english_education.entity.Answer;

import java.util.List;
import java.util.Map;

@Controller
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private LessonService lessonService;

    @GetMapping("lessons/{lessonId}/add-tests")
    public String showAddTestsForm(@PathVariable Long lessonId, Model model) {
        model.addAttribute("lessonId", lessonId);
        model.addAttribute("testWrapper", new TestWrapper());
        return "add-tests";
    }

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
    @GetMapping("/lessons/{lessonId}/edit-tests")
    public String showEditTestsForm(@PathVariable Long lessonId, Model model) {
        List<Test> tests = testService.findByLessonId(lessonId);
        TestWrapper testWrapper = new TestWrapper();
        testWrapper.setTests(tests);
        model.addAttribute("lessonId", lessonId);
        model.addAttribute("testWrapper", testWrapper);
        return "edit-tests";
    }

    @PostMapping("/edit-tests")
    public String editTests(@ModelAttribute("testWrapper") TestWrapper testWrapper) {
        for (Test test : testWrapper.getTests()) {
            // Здесь можно выполнить необходимые операции редактирования или обновления тестов в базе данных
            testService.updateTest(test);
        }
        return "redirect:/lessons";
    }
    @GetMapping("/lessons/{lessonId}/test")
    public String showTestPage(@PathVariable Long lessonId, Model model) {
        List<Test> tests = testService.findByLessonId(lessonId);

        TestWrapper testWrapper = new TestWrapper();
        testWrapper.setTests(tests);
        model.addAttribute("lessonId", lessonId);
        model.addAttribute("testWrapper", testWrapper);
        return "testing";
    }

    @PostMapping("/submitTest")
    public String submitTest(@RequestParam Long lessonId, @ModelAttribute("testWrapper") TestWrapper testWrapper) {
        for (Test test : testWrapper.getTests()) {
            System.out.println(test.getType());
        }
        // Redirect to the results page or another appropriate page
        return "redirect:/results";
    }
}
