package by.vstu.english_education.entity.Dto;

import by.vstu.english_education.entity.Test;

import java.util.ArrayList;
import java.util.List;


public class TestWrapper {
    private List<Test> tests = new ArrayList<>();

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }
}
