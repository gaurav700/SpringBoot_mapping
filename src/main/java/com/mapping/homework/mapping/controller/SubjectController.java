package com.mapping.homework.mapping.controller;

import com.mapping.homework.mapping.dto.StudentDTO;
import com.mapping.homework.mapping.dto.SubjectDTO;
import com.mapping.homework.mapping.services.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "subjects")
public class SubjectController {
    private SubjectService ss;

    public SubjectController(SubjectService ss) {
        this.ss = ss;
    }

    @GetMapping
    public ResponseEntity<List<SubjectDTO>> getSubject(){
        return ResponseEntity.ok(ss.getSubject());
    }

    @PostMapping
    public ResponseEntity<SubjectDTO> saveSubject(@RequestBody SubjectDTO inputData){
        SubjectDTO toSave = ss.saveSubject(inputData);
        return new ResponseEntity<>(toSave, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{subjectId}/professor/{professorId}")
    public ResponseEntity<SubjectDTO> subjectManyToOneMappingToProfessor(@PathVariable Long subjectId, @PathVariable Long professorId){
        SubjectDTO toSave = ss.subjectManyToOneMappingToProfessor(subjectId, professorId);
        return new ResponseEntity<>(toSave, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{subjectId}/student/{studentId}")
    public ResponseEntity<SubjectDTO> subjectManyToManyMappingToStudent(@PathVariable Long subjectId, @PathVariable Long studentId){
        SubjectDTO toSave = ss.subjectManyToManyMappingToStudent(subjectId, studentId);
        return new ResponseEntity<>(toSave, HttpStatus.CREATED);
    }
}
