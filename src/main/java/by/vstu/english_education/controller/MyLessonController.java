package by.vstu.english_education.controller;

import by.vstu.english_education.entity.Lesson;
import by.vstu.english_education.entity.User;
import by.vstu.english_education.service.LessonService;
import by.vstu.english_education.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class MyLessonController {
    @Autowired
    private LessonService lessonService;
    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('TEACHER')")
    @GetMapping("/my-lessons")
    public String myLessons(Model model, Principal principal) {
        String username = principal.getName();
        List<Lesson> lessons = lessonService.findLessonByAuthorUsername(username);
        model.addAttribute("lessons", lessons);
        return "my-lessons";
    }

    @PreAuthorize("hasRole('TEACHER')")
    @GetMapping("/delete-lesson/{id}")
    public String deleteLesson(@PathVariable Long id, Principal principal) {
        lessonService.deleteLesson(id, principal.getName());
        return "redirect:/my-lessons";
    }

    @PreAuthorize("hasRole('TEACHER')")
    @GetMapping("/add-lesson")
    public String addLessonForm(Model model) {
        model.addAttribute("lesson", new Lesson());
        return "add-lesson";
    }

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/add-lesson")
    public String addLesson(@ModelAttribute Lesson lesson, Principal principal) {
        String username = principal.getName();
        User author = userService.findByUsername(username);
        lesson.setAuthor(author);
        lessonService.saveLesson(lesson);
        return "redirect:/my-lessons";
    }

    @GetMapping("/lessons")
    public String lessons(Model model) {
        List<Lesson> lessons = lessonService.getAllLessons();
        model.addAttribute("lessons", lessons);
        return "lessons";
    }

    @GetMapping("/lessons/{id}")
    public String getLessonById(@PathVariable Long id, Model model) {
        Lesson lesson = lessonService.getLessonById(id);
        model.addAttribute("lesson", lesson);
        return "lesson";
    }

    @GetMapping("/edit-lesson/{id}")
    @PreAuthorize("hasRole('TEACHER')")
    public String showEditLessonForm(@PathVariable("id") Long lessonId, Model model) {
        Lesson lesson = lessonService.findById(lessonId);
        model.addAttribute("lesson", lesson);
        return "edit-lesson";
    }

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/edit-lesson")
    public String editLesson(@ModelAttribute("lesson") Lesson lesson) {
        lessonService.edit(lesson);
        return "redirect:/my-lessons";
    }
}
