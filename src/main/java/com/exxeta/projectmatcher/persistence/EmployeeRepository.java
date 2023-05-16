package com.exxeta.projectmatcher.persistence;

import com.exxeta.projectmatcher.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository
        extends CrudRepository<Employee, Long> { }
