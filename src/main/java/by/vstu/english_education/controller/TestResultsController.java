package by.vstu.english_education.controller;


import by.vstu.english_education.entity.Dto.TestResultsDto;
import by.vstu.english_education.service.TestResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TestResultsController {
    @Autowired
    private TestResultsService testResultsService;

    @GetMapping("/results")
    public String getResults(Model model, Authentication authentication) {
        List<TestResultsDto> testResults = testResultsService.getScoreByTests(authentication.getName());
        model.addAttribute("testResults", testResults);
        return "results";
    }
    @GetMapping("/lesson-results/{lessonId}")
    public String getLessonTestResults(@PathVariable Long lessonId, Model model) {
        List<TestResultsDto> testResults = testResultsService.getTestResultsByLesson(lessonId);
        model.addAttribute("testResults", testResults);
        return "lesson-results"; // Имя шаблона Thymeleaf для отображения результатов
    }
}
