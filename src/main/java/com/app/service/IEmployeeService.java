package com.app.service;

import java.util.List;

import com.app.model.Employee;

public interface IEmployeeService {
	
	public Integer saveEmployee(Employee emp);
	
	public List<Employee> getAllEmployees();
	
	public void deleteEmployee(Integer empId);

}
