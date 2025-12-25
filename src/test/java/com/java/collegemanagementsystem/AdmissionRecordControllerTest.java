package com.java.collegemanagementsystem;

import com.java.collegemanagementsystem.controller.AdmissionRecordController;
import com.java.collegemanagementsystem.dto.AdmissionRecordDTO;
import com.java.collegemanagementsystem.entity.Student;
import com.java.collegemanagementsystem.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class AdmissionRecordControllerTest {

    @Autowired
    private AdmissionRecordController admissionRecordController;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testCreateAdmissionWithExistingStudent() throws Exception {
        // Create and save a student first
        Student student = new Student();
        student.setName("John Doe");
        student = studentRepository.save(student);

        // Try to create an admission record with the existing student
        AdmissionRecordDTO admissionRecordDTO = new AdmissionRecordDTO();
        admissionRecordDTO.setFees(1000);
        admissionRecordDTO.setStudentId(student.getId());

        ResponseEntity<AdmissionRecordDTO> response = admissionRecordController.createAdmissionRecord(admissionRecordDTO);
        
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody().getStudentId(), "Student ID should not be null in response");
        assertEquals(student.getId(), response.getBody().getStudentId());
    }

}
