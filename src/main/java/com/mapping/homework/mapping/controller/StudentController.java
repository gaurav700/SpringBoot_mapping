package com.mapping.homework.mapping.controller;

import com.mapping.homework.mapping.dto.StudentDTO;
import com.mapping.homework.mapping.exceptions.ResourceNotFoundException;
import com.mapping.homework.mapping.services.StudentService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "students")
public class StudentController {

    private StudentService ss;

    public StudentController(StudentService ss) {
        this.ss = ss;
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getStudent(){
        return ResponseEntity.ok(ss.getStudent());
    }

    @PostMapping
    public ResponseEntity<StudentDTO> saveStudent(@RequestBody StudentDTO inputData){
        StudentDTO toSave = ss.saveStudent(inputData);
        return new ResponseEntity<>(toSave, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{studentId/admission/{admissionId}")
    public ResponseEntity<StudentDTO> studentOneToOneMappingToAdmission(@PathVariable Long studentId, @PathVariable Long admissionId){
        StudentDTO toSave = ss.studentOneToOneMappingToAdmission(studentId, admissionId);
        return new ResponseEntity<>(toSave, HttpStatus.CREATED);
    }


    @PutMapping(path = "/{studentId}/professor/{professorId}")
    public ResponseEntity<StudentDTO> studentManyToManyMappingToProfessor(@PathVariable Long studentId, @PathVariable Long professorId){
        StudentDTO toSave = ss.studentManyToManyMappingToProfessor(studentId, professorId);
        return new ResponseEntity<>(toSave, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{studentId}/subject/{subjectId}")
    public ResponseEntity<StudentDTO> studentManyToManyMappingToSubject(@PathVariable Long studentId, @PathVariable Long subjectId){
        StudentDTO toSave = ss.studentManyToManyMappingToSubject(studentId, subjectId);
        return  new ResponseEntity<>(toSave, HttpStatus.CREATED);
    }

}
