package com.exxeta.projectmatcher.service;

import com.exxeta.projectmatcher.model.Employee;
import com.exxeta.projectmatcher.model.Project;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RecommendationServiceIntegrationTests {
    @Autowired
    RecommendationService recommendationService;

    @Test
    public void should_return_employees_in_expected_order() {
        Project project = new Project(List.of("Spring Boot", "Kotlin", "AWS"));
        Employee employee1 = new Employee(
                1L,
                "App Dev",
                List.of("Android", "Kotlin", "Java")
        );
        Employee employee2 = new Employee(
                2L,
                "Backend Dev",
                List.of("Spring Boot", "Kotlin", "AWS")
        );
        List<Employee> employees = List.of(employee1, employee2);
        List<Employee> expectedEmployees = List.of(employee2, employee1);

        assertEquals(
                expectedEmployees,
                recommendationService.recommendEmployees(project, employees)
        );
    }
}
