package com.exxeta.projectmatcher.service;

import com.exxeta.projectmatcher.model.Employee;
import com.exxeta.projectmatcher.model.Project;

import java.util.List;

public interface RecommendationService {
    List<Employee> recommendEmployees(
            Project project,
            Iterable<Employee> employees
    );

    void pythonConstructor();
}
