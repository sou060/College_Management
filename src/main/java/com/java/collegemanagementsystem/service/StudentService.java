package com.java.collegemanagementsystem.service;

import com.java.collegemanagementsystem.dto.StudentDTO;
import com.java.collegemanagementsystem.entity.Professor;
import com.java.collegemanagementsystem.entity.Student;
import com.java.collegemanagementsystem.entity.Subject;
import com.java.collegemanagementsystem.exception.ResourceNotFoundException;
import com.java.collegemanagementsystem.repository.ProfessorRepository;
import com.java.collegemanagementsystem.repository.StudentRepository;
import com.java.collegemanagementsystem.repository.SubjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final ProfessorRepository professorRepository;

    public StudentService(StudentRepository studentRepository, SubjectRepository subjectRepository, ProfessorRepository professorRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.professorRepository = professorRepository;
    }

    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        Student savedStudent = studentRepository.save(student);
        return convertToDTO(savedStudent);
    }

    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        return convertToDTO(student);
    }

    @Transactional
    public StudentDTO updateStudentById(Long id, StudentDTO studentDTO) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        existingStudent.setName(studentDTO.getName());
        return convertToDTO(studentRepository.save(existingStudent));
    }

    @Transactional
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public StudentDTO enrollStudentInSubject(Long studentId, Long subjectId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + subjectId));
        
        student.getSubjects().add(subject);
        return convertToDTO(studentRepository.save(student));
    }

    @Transactional
    public StudentDTO linkStudentToProfessor(Long studentId, Long professorId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
        Professor professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new ResourceNotFoundException("Professor not found with id: " + professorId));

        student.getProfessors().add(professor);
        return convertToDTO(studentRepository.save(student));
    }

    private StudentDTO convertToDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        if (student.getSubjects() != null) {
            dto.setSubjectIds(student.getSubjects().stream().map(Subject::getId).collect(Collectors.toList()));
        }
        if (student.getProfessors() != null) {
            dto.setProfessorIds(student.getProfessors().stream().map(Professor::getId).collect(Collectors.toList()));
        }
        if (student.getAdmissionRecord() != null) {
            dto.setAdmissionRecordId(student.getAdmissionRecord().getId());
        }
        return dto;
    }
}
