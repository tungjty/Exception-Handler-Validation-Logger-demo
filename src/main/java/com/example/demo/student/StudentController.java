package com.example.demo.student;

import com.example.demo.exception.EmailDuplicatedException;
import com.example.demo.exception.StudentNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;
    private final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        LOGGER.info("LOGGER INFO IN GETSTUDENTS() METHOD");
        return studentService.getStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student)
            throws EmailDuplicatedException {
        LOGGER.info("LOGGER INFO IN REGISTERNEWSTUDENT() METHOD");
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id)
            throws StudentNotFoundException {
        LOGGER.info("LOGGER INFO IN DELETESTUDENT() METHOD");
        studentService.deleteStudent(id);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {

        LOGGER.info("LOGGER INFO IN UPDATESTUDENT() METHOD");
        studentService.updateStudent(id, name, email);
    }
}
