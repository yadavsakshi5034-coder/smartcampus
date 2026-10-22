
package com.campus.smartcampus;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class StudentDirectory {

    private final Map<String, Student> students = new HashMap<>();

    public StudentDirectory() {
        students.put("101",
                new Student("Aarav Sharma", "Computer Engineering"));

        students.put("102",
                new Student("Priya Patel", "Information Technology"));

        students.put("103",
                new Student("Rahul Verma", "Electronics"));

        students.put("104",
                new Student("Sneha Shah", "Computer Engineering"));
    }

    public Student searchStudent(String studentId) {
        return students.get(studentId);
    }

    public static class Student {

        private final String name;
        private final String department;

        public Student(String name, String department) {
            this.name = name;
            this.department = department;
        }

        public String getName() {
            return name;
        }

        public String getDepartment() {
            return department;
        }
    }
}
