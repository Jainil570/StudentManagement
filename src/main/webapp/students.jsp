<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.student.management.Student" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student List</title>
    <style>
        table { width: 80%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid black; padding: 10px; text-align: left; }
        th { background-color: #f2f2f2; }
        .no-data { color: red; font-weight: bold; }
    </style>
</head>
<body>
    <h2>All Students</h2>
    
    <%
        List<Student> students = (List<Student>) request.getAttribute("students");
        if (students != null && !students.isEmpty()) {
    %>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Course</th>
            <th>Actions</th>
        </tr>
        <%
            for (Student s : students) {
        %>
        <tr>
            <td><%= s.getId() %></td>
            <td><%= s.getName() %></td>
            <td><%= s.getEmail() %></td>
            <td><%= s.getCourse() %></td>
            <td>
                <a href="student?action=edit&id=<%= s.getId() %>">Edit</a> |
                <a href="student?action=delete&id=<%= s.getId() %>" onclick="return confirm('Are you sure you want to delete this student?');">Delete</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
    <%
        } else {
    %>
    <p class="no-data">No students found.</p>
    <%
        }
    %>
    
    <br>
    <a href="index.jsp">Go Back</a>
</body>
</html>
