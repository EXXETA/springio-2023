package com.exxeta.projectmatcher.controller;

import com.exxeta.projectmatcher.model.Employee;
import com.exxeta.projectmatcher.model.Project;
import com.exxeta.projectmatcher.service.EmployeeService;
import com.exxeta.projectmatcher.service.RecommendationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeControllerUnitTests {
    EmployeeService employeeService = Mockito.mock(EmployeeService.class);
    RecommendationService recommendationService = Mockito.mock(RecommendationService.class);
    EmployeeController employeeController = new EmployeeController(employeeService, recommendationService);

    @Test
    public void controller_should_return_employees_returned_by_recommendationservice() {
        Project project = new Project(List.of("Java", "Kotlin"));
        List<Employee> employees = List.of(
            new Employee(1L, "John Doe", List.of("Java")),
            new Employee(2L, "Jane Doe", List.of("Kotlin"))
        );
        List<Employee> recommendedEmployees = List.of(
            new Employee(2L, "Jane Doe", List.of("Kotlin"))
        );
        Mockito.when(employeeService.findAll()).thenReturn(employees);
        Mockito.when(
                recommendationService.recommendEmployees(project, employees)
        ).thenReturn(recommendedEmployees);

        assertEquals(recommendedEmployees, employeeController.getRecommendation(project));
        Mockito.verify(
                recommendationService, Mockito.times(1)
        ).recommendEmployees(project, employees);
        Mockito.verify(
                employeeService, Mockito.times(1)
        ).findAll();
    }
}
