package com.java.collegemanagementsystem.controller;

import com.java.collegemanagementsystem.dto.ProfessorDTO;
import com.java.collegemanagementsystem.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professor")
public class ProfessorController {
    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping
    public ResponseEntity<ProfessorDTO> createProfessor(@Valid @RequestBody ProfessorDTO professorDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(professorService.createProfessor(professorDTO));
    }

    @GetMapping
    public ResponseEntity<List<ProfessorDTO>> getAllProfessors() {
        return ResponseEntity.status(HttpStatus.OK).body(professorService.getAllProfessors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDTO> getProfessorById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(professorService.getProfessorById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorDTO> updateProfessorById(@PathVariable Long id, @Valid @RequestBody ProfessorDTO professorDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(professorService.updateProfessorById(id, professorDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProfessorById(@PathVariable Long id) {
        professorService.deleteProfessor(id);
        return ResponseEntity.status(HttpStatus.OK).body("Professor deleted successfully");
    }
}
