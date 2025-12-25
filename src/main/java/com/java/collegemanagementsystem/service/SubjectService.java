package com.java.collegemanagementsystem.service;

import com.java.collegemanagementsystem.dto.SubjectDTO;
import com.java.collegemanagementsystem.entity.Professor;
import com.java.collegemanagementsystem.entity.Student;
import com.java.collegemanagementsystem.entity.Subject;
import com.java.collegemanagementsystem.exception.ResourceNotFoundException;
import com.java.collegemanagementsystem.repository.ProfessorRepository;
import com.java.collegemanagementsystem.repository.SubjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final ProfessorRepository professorRepository;

    public SubjectService(SubjectRepository subjectRepository, ProfessorRepository professorRepository) {
        this.subjectRepository = subjectRepository;
        this.professorRepository = professorRepository;
    }

    public SubjectDTO createSubject(SubjectDTO subjectDTO) {
        Subject subject = new Subject();
        subject.setTitle(subjectDTO.getTitle());
        return convertToDTO(subjectRepository.save(subject));
    }

    public List<SubjectDTO> getAllSubjects() {
        return subjectRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public SubjectDTO getSubjectById(Long id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + id));
        return convertToDTO(subject);
    }

    @Transactional
    public SubjectDTO updateSubjectById(Long id, SubjectDTO subjectDTO) {
        Subject existingSubject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + id));
        existingSubject.setTitle(subjectDTO.getTitle());
        return convertToDTO(subjectRepository.save(existingSubject));
    }

    @Transactional
    public void deleteSubject(Long id) {
        if (!subjectRepository.existsById(id)) {
            throw new ResourceNotFoundException("Subject not found with id: " + id);
        }
        subjectRepository.deleteById(id);
    }

    @Transactional
    public SubjectDTO assignProfessorToSubject(Long subjectId, Long professorId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + subjectId));
        Professor professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new ResourceNotFoundException("Professor not found with id: " + professorId));

        subject.setProfessor(professor);
        return convertToDTO(subjectRepository.save(subject));
    }

    private SubjectDTO convertToDTO(Subject subject) {
        SubjectDTO dto = new SubjectDTO();
        dto.setId(subject.getId());
        dto.setTitle(subject.getTitle());
        if (subject.getProfessor() != null) {
            dto.setProfessorId(subject.getProfessor().getId());
        }
        if (subject.getStudents() != null) {
            dto.setStudentIds(subject.getStudents().stream().map(Student::getId).collect(Collectors.toList()));
        }
        return dto;
    }
}
