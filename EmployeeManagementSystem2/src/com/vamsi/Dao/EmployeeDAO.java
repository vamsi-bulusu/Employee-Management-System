package com.vamsi.Dao;

import java.util.List;

import com.vamsi.Entities.Employee;

public interface EmployeeDAO {

	List<Employee> get();
	
	Employee get(int id);
	
	boolean save(Employee e);
	
	boolean update(Employee e);
	
	boolean delete(int id);
}
