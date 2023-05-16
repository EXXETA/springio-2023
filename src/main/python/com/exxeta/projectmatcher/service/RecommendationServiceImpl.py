import java
import numpy as np
from typing import List


Employee = java.type("com.exxeta.projectmatcher.model.Employee")
Project = java.type("com.exxeta.projectmatcher.model.Project")


class RecommendationServiceImpl:
    @staticmethod
    def recommendEmployees(project: Project, employees: List[Employee]) -> List[Employee]:
        project_vector = np.array(list(map(lambda skill: 1, project.skills())))
        
        employee_vectors = {
            employee: to_vector(employee.getSkills(), project.skills())
            for employee in employees
        }
        
        distances = {
            employee: np.linalg.norm(project_vector - skill_vector)
            for employee, skill_vector in employee_vectors.items()
        }
        
        ordered = dict(sorted(distances.items(), key=lambda distance: distance[1]))
        
        return list(ordered.keys())

        
def to_vector(employee_skills, searched_skills):
    return np.array(list(map(lambda skill: int(skill in employee_skills), searched_skills)))