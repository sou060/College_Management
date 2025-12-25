package com.java.collegemanagementsystem;

import com.java.collegemanagementsystem.controller.AdmissionRecordController;
import com.java.collegemanagementsystem.entity.AdmissionRecord;
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
        AdmissionRecord admissionRecord = new AdmissionRecord();
        admissionRecord.setFees(1000);
        
        // Simulating what might happen if the student is passed with just ID
        Student studentRef = new Student();
        studentRef.setId(student.getId());
        admissionRecord.setStudent(studentRef);

        ResponseEntity<AdmissionRecord> response = admissionRecordController.createAdmissionRecord(admissionRecord);
        
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody().getStudent(), "Student should not be null in response");
        assertEquals(student.getId(), response.getBody().getStudent().getId());
        assertNotNull(response.getBody().getStudent().getName(), "Student name should be populated");
        assertEquals("John Doe", response.getBody().getStudent().getName());
    }

    @Test
    public void testCreateAdmissionWithNewStudent() throws Exception {
        AdmissionRecord admissionRecord = new AdmissionRecord();
        admissionRecord.setFees(2000);
        
        Student newStudent = new Student();
        newStudent.setName("Jane Smith");
        admissionRecord.setStudent(newStudent);

        ResponseEntity<AdmissionRecord> response = admissionRecordController.createAdmissionRecord(admissionRecord);
        
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody().getStudent(), "Student should not be null in response");
        assertEquals("Jane Smith", response.getBody().getStudent().getName());
        assertNotNull(response.getBody().getStudent().getId(), "New student should have an ID");
    }
}
