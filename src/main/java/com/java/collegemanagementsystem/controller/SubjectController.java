package com.java.collegemanagementsystem.controller;

import com.java.collegemanagementsystem.dto.SubjectDTO;
import com.java.collegemanagementsystem.service.SubjectService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {
    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping
    public ResponseEntity<SubjectDTO> createSubject(@Valid @RequestBody SubjectDTO subjectDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subjectService.createSubject(subjectDTO));
    }

    @GetMapping
    public ResponseEntity<List<SubjectDTO>> getAllSubjects() {
        return ResponseEntity.status(HttpStatus.OK).body(subjectService.getAllSubjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectDTO> getSubjectById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(subjectService.getSubjectById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubjectDTO> updateSubjectById(@PathVariable Long id, @Valid @RequestBody SubjectDTO subjectDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(subjectService.updateSubjectById(id, subjectDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubjectById(@PathVariable Long id) {
        subjectService.deleteSubject(id);
        return ResponseEntity.status(HttpStatus.OK).body("Subject deleted successfully");
    }

    @PutMapping("/{subjectId}/professor/{professorId}")
    public ResponseEntity<SubjectDTO> assignProfessorToSubject(@PathVariable Long subjectId, @PathVariable Long professorId) {
        return ResponseEntity.status(HttpStatus.OK).body(subjectService.assignProfessorToSubject(subjectId, professorId));
    }
}
