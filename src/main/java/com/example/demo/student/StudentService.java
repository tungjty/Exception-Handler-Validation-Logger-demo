package com.example.demo.student;

import com.example.demo.exception.EmailDuplicatedException;
import com.example.demo.exception.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository ;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) throws EmailDuplicatedException {

        Optional<Student> studentOptional =
                studentRepository.findStudentByEmail(student.getEmail());

        if(studentOptional.isPresent()) {
//            throw new IllegalStateException("Email already existed :(");
            throw new EmailDuplicatedException("email already existed :(");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) throws StudentNotFoundException {
        boolean existsId = studentRepository.existsById(studentId);
        if(!existsId)
            throw new StudentNotFoundException(
                    "Student with ID '" + studentId + "' does not exist.");
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long id, String name, String email) {
        Student optionalStudent =  studentRepository.findById(id)
                .orElseThrow( () -> new IllegalStateException(
                        "Student with ID '" + id + "' does not exist :( "));

        if(Objects.equals(optionalStudent.getEmail(), email))
            throw new IllegalStateException("Email : " + email + " already existed :(");

        if(email != null && email.length() > 0
                && !Objects.equals(optionalStudent.getEmail(), email))
            optionalStudent.setEmail(email);

        if(name != null && name.length() > 0
                && !Objects.equals(optionalStudent.getName(), name))
            optionalStudent.setName(name);

        // NOTE: we need not call save() method
        //studentRepository.save(student);

    }
}
