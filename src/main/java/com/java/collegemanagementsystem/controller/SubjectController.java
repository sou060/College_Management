package com.java.collegemanagementsystem.controller;

import com.java.collegemanagementsystem.dto.SubjectDTO;
import com.java.collegemanagementsystem.service.SubjectService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@RestController
@Validated
@RequestMapping({"/api/v1/subject", "/api/subject"})
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
    public ResponseEntity<SubjectDTO> getSubjectById(@PathVariable @Positive Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(subjectService.getSubjectById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubjectDTO> updateSubjectById(@PathVariable @Positive Long id, @Valid @RequestBody SubjectDTO subjectDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(subjectService.updateSubjectById(id, subjectDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubjectById(@PathVariable @Positive Long id) {
        subjectService.deleteSubject(id);
        return ResponseEntity.status(HttpStatus.OK).body("Subject deleted successfully");
    }

    @PutMapping("/{subjectId}/professor/{professorId}")
    public ResponseEntity<SubjectDTO> assignProfessorToSubject(@PathVariable @Positive Long subjectId, @PathVariable @Positive Long professorId) {
        return ResponseEntity.status(HttpStatus.OK).body(subjectService.assignProfessorToSubject(subjectId, professorId));
    }

    @DeleteMapping("/{subjectId}/professor")
    public ResponseEntity<SubjectDTO> unassignProfessorFromSubject(@PathVariable @Positive Long subjectId) {
        return ResponseEntity.ok(subjectService.unassignProfessorFromSubject(subjectId));
    }
}
