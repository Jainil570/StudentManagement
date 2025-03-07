package com.student.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    // Method to insert a student into the database
	public static boolean addStudent(Student student) {
	    boolean success = false;
	    try (Connection con = DBConnection.getConnection()) {
	        if (con == null) {
	            System.err.println("❌ Database connection is null! Check DBConnection.java");
	            return false;
	        }
	        String query = "INSERT INTO students (name, email, course) VALUES (?, ?, ?)";
	        PreparedStatement ps = con.prepareStatement(query);
	        ps.setString(1, student.getName());
	        ps.setString(2, student.getEmail());
	        ps.setString(3, student.getCourse());
	        success = ps.executeUpdate() > 0;
	    } catch (SQLException e) {
	        System.err.println("❌ SQL Error: " + e.getMessage());
	        e.printStackTrace();
	    }
	    return success;
	}


    // Method to get all students from the database
    public static List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT * FROM students";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student student = new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("course")
                );
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
 // Retrieve a single student by ID
    public static Student getStudentById(int id) {
        Student student = null;
        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT * FROM students WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                student = new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("course")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    // Update a student's details
    public static boolean updateStudent(Student student) {
        boolean success = false;
        try (Connection con = DBConnection.getConnection()) {
            String query = "UPDATE students SET name=?, email=?, course=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setString(3, student.getCourse());
            ps.setInt(4, student.getId());
            success = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    // Delete a student by ID
    public static boolean deleteStudent(int id) {
        boolean success = false;
        try (Connection con = DBConnection.getConnection()) {
            String query = "DELETE FROM students WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            success = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

}
