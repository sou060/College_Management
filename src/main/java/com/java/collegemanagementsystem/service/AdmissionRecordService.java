package com.java.collegemanagementsystem.service;

import com.java.collegemanagementsystem.dto.AdmissionRecordDTO;
import com.java.collegemanagementsystem.entity.AdmissionRecord;
import com.java.collegemanagementsystem.entity.Student;
import com.java.collegemanagementsystem.exception.ResourceNotFoundException;
import com.java.collegemanagementsystem.repository.AdmissionRecordRepository;
import com.java.collegemanagementsystem.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdmissionRecordService {
    private final AdmissionRecordRepository admissionRecordRepository;
    private final StudentRepository studentRepository;

    public AdmissionRecordService(AdmissionRecordRepository admissionRecordRepository, StudentRepository studentRepository) {
        this.admissionRecordRepository = admissionRecordRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional
    public AdmissionRecordDTO createAdmission(AdmissionRecordDTO admissionRecordDTO) {
        if (admissionRecordDTO.getStudentId() == null) {
            throw new IllegalArgumentException("Student ID must be provided");
        }
        Long studentId = admissionRecordDTO.getStudentId();
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
        
        // Ensure student doesn't already have an admission record
        if (student.getAdmissionRecord() != null) {
            throw new IllegalStateException("Student already has an admission record");
        }
        
        AdmissionRecord admissionRecord = new AdmissionRecord();
        admissionRecord.setFees(admissionRecordDTO.getFees());
        admissionRecord.setStudent(student);
        
        return convertToDTO(admissionRecordRepository.save(admissionRecord));
    }

    public void deleteAdmission(Long id) {
        admissionRecordRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found with id:" + id));
        admissionRecordRepository.deleteById(id);
    }

    private AdmissionRecordDTO convertToDTO(AdmissionRecord admissionRecord) {
        AdmissionRecordDTO dto = new AdmissionRecordDTO();
        dto.setId(admissionRecord.getId());
        dto.setFees(admissionRecord.getFees());
        if (admissionRecord.getStudent() != null) {
            dto.setStudentId(admissionRecord.getStudent().getId());
        }
        return dto;
    }
}
