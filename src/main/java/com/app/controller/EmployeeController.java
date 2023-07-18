package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Employee;
import com.app.service.IEmployeeService;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "*")
public class EmployeeController {

	@Autowired
	private IEmployeeService employeeService;

	@PostMapping("/save")
	public ResponseEntity<String> saveEmployee(@RequestBody Employee emp) {
		ResponseEntity<String> respEntity = null;
		try {
			Integer empId = employeeService.saveEmployee(emp);
			respEntity = new ResponseEntity<String>("Employee saved with id '" + empId + "' successfully",
					HttpStatus.OK);
		} catch (Exception e) {
			respEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return respEntity;
	}

	@GetMapping("/getEmpData")
	public ResponseEntity<?> getEmployeesData() {
		ResponseEntity<?> respEntity = null;
		List<Employee> emps = employeeService.getAllEmployees();
		if (emps == null || emps.isEmpty()) {
			String msg = "No Data available";
			respEntity = new ResponseEntity<String>(msg, HttpStatus.OK);
		} else {
			respEntity = new ResponseEntity<List<Employee>>(emps, HttpStatus.OK);
		}
		return respEntity;
	}
	
	@DeleteMapping("/delete/{empId}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Integer empId) {
		ResponseEntity<String> respEntity = null;
		employeeService.deleteEmployee(empId);
		employeeService.getAllEmployees();
		return respEntity;
	}

}
