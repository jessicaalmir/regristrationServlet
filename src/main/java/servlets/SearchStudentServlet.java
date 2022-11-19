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
public class SaveStudentServlet extends HttpServlet {

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

            //Select the recent saved student
            Student savedStudent = studentDAO.findStudentbyId(id);

            //Display response
            PrintWriter out = resp.getWriter();
            out.println("<html><body>" +
                    "<h3>Student successfully registered</h3>" +
                    "<table style='background-color:#C48EC5'>\n" +
                    "            <tr>\n" +
                    "            <td>Id</td>\n" +
                    "            <td>"+savedStudent.getID()+"</td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "            <td>Full Name</td>\n" +
                    "            <td>"+savedStudent.getName()+"</td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "            <td>UserName</td>\n" +
                    "            <td>"+savedStudent.getUserName()+"</td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "            <td>Password</td>\n" +
                    "            <td>"+savedStudent.getPass()+"</td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "            <td>Address</td>\n" +
                    "            <td>"+savedStudent.getAddr()+"</td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "            <td>Age</td>\n" +
                    "            <td>"+savedStudent.getAge()+"</td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "            <td>Qualification</td>\n" +
                    "            <td>"+savedStudent.getQual()+"</td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "            <td>Percentage</td>\n" +
                    "            <td>"+savedStudent.getPercent()+"</td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "            <td>Year Passed</td>\n" +
                    "            <td>"+savedStudent.getYear()+"</td>\n" +
                    "            </tr>\n" +
                    "            </table>" +
                    "            <a href='/search'>Search</a>" +
                    "</body></html>");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
