
package com.campus.smartcampus;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {

    private final StudentDirectory studentDirectory;

    public StudentController(StudentDirectory studentDirectory) {
        this.studentDirectory = studentDirectory;
    }

    @GetMapping("/students")
    public String students(
            @RequestParam(required = false) String studentId,
            Model model) {

        model.addAttribute("studentId", studentId);

        if (studentId != null && !studentId.isBlank()) {
            StudentDirectory.Student student =
                    studentDirectory.searchStudent(studentId.trim());

            model.addAttribute("student", student);
            model.addAttribute("searched", true);
        }

        return "students";
    }
}
