package com.java.collegemanagementsystem.service;

import com.java.collegemanagementsystem.dto.ProfessorDTO;
import com.java.collegemanagementsystem.entity.Professor;
import com.java.collegemanagementsystem.entity.Student;
import com.java.collegemanagementsystem.entity.Subject;
import com.java.collegemanagementsystem.exception.ResourceNotFoundException;
import com.java.collegemanagementsystem.repository.ProfessorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public ProfessorDTO createProfessor(ProfessorDTO professorDTO) {
        Professor professor = new Professor();
        professor.setTitle(professorDTO.getTitle());
        return convertToDTO(professorRepository.save(professor));
    }

    public List<ProfessorDTO> getAllProfessors() {
        return professorRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ProfessorDTO getProfessorById(Long id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor not found with id: " + id));
        return convertToDTO(professor);
    }

    @Transactional
    public ProfessorDTO updateProfessorById(Long id, ProfessorDTO professorDTO) {
        Professor existingProfessor = professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor not found with id: " + id));
        existingProfessor.setTitle(professorDTO.getTitle());
        return convertToDTO(professorRepository.save(existingProfessor));
    }

    @Transactional
    public void deleteProfessor(Long id) {
        if (!professorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Professor not found with id: " + id);
        }
        professorRepository.deleteById(id);
    }

    private ProfessorDTO convertToDTO(Professor professor) {
        ProfessorDTO dto = new ProfessorDTO();
        dto.setId(professor.getId());
        dto.setTitle(professor.getTitle());
        if (professor.getSubjects() != null) {
            dto.setSubjectIds(professor.getSubjects().stream().map(Subject::getId).collect(Collectors.toList()));
        }
        if (professor.getStudents() != null) {
            dto.setStudentIds(professor.getStudents().stream().map(Student::getId).collect(Collectors.toList()));
        }
        return dto;
    }
}
