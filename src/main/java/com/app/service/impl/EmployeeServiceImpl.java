package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Employee;
import com.app.repository.EmployeeRepository;
import com.app.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Integer saveEmployee(Employee emp) {
		Employee empployee = employeeRepository.save(emp);
		return empployee.getEmpId();
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> emps = employeeRepository.findAll();
		return emps;
	}

	@Override
	public void deleteEmployee(Integer empId) {
		employeeRepository.deleteById(empId);
	}
}
