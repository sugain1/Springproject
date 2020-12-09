package com.springmvcdemo.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springmvcdemo.demo.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
