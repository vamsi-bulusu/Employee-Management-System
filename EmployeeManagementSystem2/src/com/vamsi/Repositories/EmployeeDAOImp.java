package com.vamsi.Repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.vamsi.Dao.EmployeeDAO;
import com.vamsi.Entities.Employee;
import com.vamsi.Utilities.DBConnectionUtil;

public class EmployeeDAOImp implements EmployeeDAO {

	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement; 
	
	
	@Override
	public List<Employee> get() {
		
		List<Employee> list = null;
		
		Employee employee = null;
		
		try {
			//create a list of employee
			list = new ArrayList<Employee>();
			
			//query string
			String sql = "SELECT * FROM tbl_employee";
			
			//get db connection
			connection = DBConnectionUtil.openConnection();
			
			//Create Statement
			statement = (Statement) connection.createStatement();
			
			//Execute Query
			resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				employee = new Employee();
				employee.setDepartment(resultSet.getString("department"));
				employee.setName(resultSet.getString("name"));
				employee.setDob(resultSet.getString("dob"));
				employee.setId(resultSet.getInt("id"));
				
				list.add(employee);
			}   		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	@Override
	public boolean save(Employee e) {
		boolean flag = false;
		try {
		  String sql = "INSERT INTO tbl_employee(name, dob, department)VALUES('"+e.getName()+"','"+e.getDob()+"','"+e.getDepartment()+"')";
		  connection = DBConnectionUtil.openConnection();
		  preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
		  preparedStatement.executeUpdate();
		  flag = true;
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		return flag;
	}


	@Override
	public Employee get(int id) {
		Employee employee = null;
		try {
				employee = new Employee();
				String sql = "SELECT * FROM tbl_employee WHERE id="+id;
				connection = DBConnectionUtil.openConnection();
				statement = (Statement) connection.createStatement();
				resultSet = statement.executeQuery(sql);
				while(resultSet.next()) {
					employee.setId(resultSet.getInt("id"));
					employee.setName(resultSet.getString("name"));
					employee.setDob(resultSet.getString("dob"));
					employee.setDepartment(resultSet.getString("department"));
			   } 
		   }
		catch (SQLException ex) {
           ex.printStackTrace();
		}
		return employee;
	}


	@Override
	public boolean update(Employee e) {
		boolean flag = false;
		try {
		 String sql = "UPDATE tbl_employee SET name='"+e.getName()+"',dob='"+e.getDob()+"',department='"+e.getDepartment()+"'WHERE id="+e.getId();
		 connection = DBConnectionUtil.openConnection();
		 preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
		 preparedStatement.executeUpdate();
		 flag = true;
		}
		catch (SQLException ex) {
		  ex.printStackTrace();
		}
		return flag;
	}


	@Override
	public boolean delete(int id) {
		boolean flag = false;
		try {
			String sql = "DELETE FROM tbl_employee WHERE id="+id;
		    connection = DBConnectionUtil.openConnection();
		    preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
		    preparedStatement.executeUpdate();
		    flag = true;
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		return flag;
	}
			
}
