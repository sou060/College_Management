package com.java.collegemanagementsystem.controller;

import com.java.collegemanagementsystem.dto.StudentDTO;
import com.java.collegemanagementsystem.service.StudentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping({"/api/v1/student", "/api/student"})
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@Valid @RequestBody StudentDTO studentDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(studentDTO));
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable @Positive Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudentById(@PathVariable @Positive Long id, @Valid @RequestBody StudentDTO studentDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.updateStudentById(id, studentDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable @Positive Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.OK).body("Student deleted successfully");
    }

    @PostMapping("/{studentId}/subjects/{subjectId}")
    public ResponseEntity<StudentDTO> enrollStudentInSubject(@PathVariable @Positive Long studentId, @PathVariable @Positive Long subjectId) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.enrollStudentInSubject(studentId, subjectId));
    }

    @PostMapping("/{studentId}/professors/{professorId}")
    public ResponseEntity<StudentDTO> linkStudentToProfessor(@PathVariable @Positive Long studentId, @PathVariable @Positive Long professorId) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.linkStudentToProfessor(studentId, professorId));
    }


}
