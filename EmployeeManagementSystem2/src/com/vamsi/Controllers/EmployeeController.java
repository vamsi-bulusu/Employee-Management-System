package com.vamsi.Controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vamsi.Entities.Employee;
import com.vamsi.Repositories.EmployeeDAOImp;

/**
 * Servlet implementation class EmployeeController
 */
@WebServlet(description = "Displays list of employees", urlPatterns = { "/EmployeeController" })
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	 EmployeeDAOImp employeeDao = null;
	 RequestDispatcher dispatcher= null;
    public EmployeeController() {
        employeeDao = new EmployeeDAOImp();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
	    if(action == null)
	    	 action = "LIST";
	    switch(action) {
			case "LIST":
				listEmployees(request, response);
				break;
			case "EDIT":
				getSingleEmployee(request,response);
				break;
			case "DELETE":
			    deleteEmployee(request,response);
			    break;
			default:
				listEmployees(request, response);
				break;
	    }
	}
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("firstname");
	    String dob = request.getParameter("dob");
	    String department = request.getParameter("department");
	    String id = request.getParameter("id");
	    Employee e = new Employee();
	    e.setName(name);
	    e.setDob(dob);
	    e.setDepartment(department);
	    if(id.isEmpty() || id == null) {
	    	 if(employeeDao.save(e)) {
	 	    	request.setAttribute("message","Saved Succesfully");
	 	    }
	    }
	    else {
	      e.setId(Integer.parseInt(id));
	      if(employeeDao.update(e)) {
	    	  request.setAttribute("message","Updated Succesfully");
	       }
	    }
        listEmployees(request, response);  
	}
	
	public void listEmployees(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    
	   List<Employee> list = employeeDao.get();
		
	   request.setAttribute("list", list);
	
	   dispatcher = request.getRequestDispatcher("/Views/employee-list.jsp");
    
	   dispatcher.forward(request, response);
	}
	
	
	public void getSingleEmployee(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
	  
		String id = request.getParameter("id");
		Employee emp = employeeDao.get(Integer.parseInt(id)); 
		request.setAttribute("employee", emp);
		dispatcher = request.getRequestDispatcher("Views/employee-add.jsp");
		dispatcher.forward(request, response);	
	}
	
	public void deleteEmployee(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
		String id = request.getParameter("id");
		if(employeeDao.delete(Integer.parseInt(id))){
			request.setAttribute("message", "Deleted succesfully!");
		}
		listEmployees(request, response);
	}

}
