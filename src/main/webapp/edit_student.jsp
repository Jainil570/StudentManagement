<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.student.management.Student" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Student</title>
</head>
<body>
    <h2>Edit Student</h2>
    <%
        Student student = (Student) request.getAttribute("student");
        if (student != null) {
    %>
    <form action="student" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<%= student.getId() %>">
        Name: <input type="text" name="name" value="<%= student.getName() %>" required><br><br>
        Email: <input type="email" name="email" value="<%= student.getEmail() %>" required><br><br>
        Course: <input type="text" name="course" value="<%= student.getCourse() %>" required><br><br>
        <input type="submit" value="Update">
    </form>
    <%
        } else {
            out.println("Student not found.");
        }
    %>
    <br>
    <a href="student">Back to List</a>
</body>
</html>
