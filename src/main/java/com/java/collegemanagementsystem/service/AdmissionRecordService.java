package com.java.collegemanagementsystem.service;

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
    public AdmissionRecord createAdmission(AdmissionRecord admissionRecord) {
        return admissionRecordRepository.save(admissionRecord);
    }

    public void deleteAdmission(Long id) {
            admissionRecordRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found with id:"+id));
            admissionRecordRepository.deleteById(id);
    }
}
