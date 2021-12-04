package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student maria = new Student(
                    "Maria",
                    "maria.jones@amigoscode.edu",
                    LocalDate.of(2000, Month.JANUARY, 5)
            );
            Student alex = new Student(
                    "Alex",
                    "alex@amigoscode.edu",
                    LocalDate.of(2004, Month.JANUARY, 22)
            );
            Student tunghoang = new Student(
                    "Tùng Hoàng",
                    "tung.hoang@gmail.com",
                    LocalDate.of(1984, Month.FEBRUARY, 18)

            );
            studentRepository.saveAll(
                    List.of(maria, alex, tunghoang)
            );
        };
    }
}
