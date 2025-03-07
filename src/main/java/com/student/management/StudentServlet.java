package com.student.management;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Handle GET requests (list, edit, delete)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            // Default: List all students
            List<Student> students = StudentDAO.getAllStudents();
            request.setAttribute("students", students);
            request.getRequestDispatcher("students.jsp").forward(request, response);
        } else if (action.equals("edit")) {
            // Edit: Retrieve student by ID
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                Student student = StudentDAO.getStudentById(id);
                request.setAttribute("student", student);
                request.getRequestDispatcher("edit_student.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                response.getWriter().println("Invalid student ID.");
            }
        } else if (action.equals("delete")) {
            // Delete: Remove student by ID
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                boolean success = StudentDAO.deleteStudent(id);
                if (success) {
                    response.sendRedirect("student");
                } else {
                    response.getWriter().println("Error deleting student.");
                }
            } catch (NumberFormatException e) {
                response.getWriter().println("Invalid student ID.");
            }
        }
    }

    // Handle POST requests (insert and update)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null && action.equals("update")) {
            // Update an existing student
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String course = request.getParameter("course");

                if (name == null || email == null || course == null) {
                    response.getWriter().println("Invalid input!");
                    return;
                }

                Student student = new Student(id, name, email, course);
                boolean success = StudentDAO.updateStudent(student);

                if (success) {
                    response.sendRedirect("student");
                } else {
                    response.getWriter().println("Error updating student.");
                }
            } catch (NumberFormatException e) {
                response.getWriter().println("Invalid student ID.");
            }
        } else {
            // Insert new student
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String course = request.getParameter("course");

            if (name == null || email == null || course == null) {
                response.getWriter().println("Invalid input!");
                return;
            }

            Student student = new Student(0, name, email, course); // ID auto-generated
            boolean isSuccess = StudentDAO.addStudent(student);

            if (isSuccess) {
                response.sendRedirect("student");
            } else {
                response.getWriter().println("Error adding student.");
            }
        }
    }
}
