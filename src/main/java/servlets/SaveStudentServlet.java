package servlets;

import bean.Student;
import dao.StudentDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

//import db Connection
@WebServlet("/")
public class StudentServlet extends HttpServlet {

    //DAO to interact with database
    StudentDAO studentDAO;

    public void init() throws ServletException {
        try {
            studentDAO = new StudentDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        //Call the action method
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("fullName");
            String userName = req.getParameter("userName");
            String pass = req.getParameter("password");
            String address = req.getParameter("address");
            int age = Integer.parseInt(req.getParameter("age"));
            String qualification = req.getParameter("qualification");
            String percent = req.getParameter("percentage");
            String year = req.getParameter("year");

            //Cretae new Student Object
            Student newStudent = new Student(id, name, userName, pass, address, age, qualification, percent, year);
            studentDAO.saveStudent(newStudent);

            //Display response
            PrintWriter out = resp.getWriter();
            out.println("<html><body>" +
                    "<h1>Student Saved Susccessfully</h1></br>" +
                    "<a href=''" +
                    "</body></html>");
            }
            catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

}
