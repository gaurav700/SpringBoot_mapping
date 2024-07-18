package com.mapping.homework.mapping.controller;

import com.mapping.homework.mapping.dto.ProfessorDTO;
import com.mapping.homework.mapping.services.ProfessorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.prefs.PreferencesFactory;

@RestController
@RequestMapping(path = "professors")
public class ProfessorController {
    private ProfessorService ps;

    public ProfessorController(ProfessorService ps) {
        this.ps = ps;
    }

    @GetMapping
    public ResponseEntity<List<ProfessorDTO>> getProfessor(){
        return ResponseEntity.ok(ps.getProfessor());
    }

    @PostMapping
    public ResponseEntity<ProfessorDTO> saveProfessor(@RequestBody ProfessorDTO inputData){
        ProfessorDTO toSave = ps.saveProfessor(inputData);
        return new ResponseEntity<>(toSave, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{professorId}/subject/{subjectId}")
    public ResponseEntity<ProfessorDTO> professorOneToManyMappingWithSubject(@PathVariable Long professorId,
                                                                             @PathVariable Long subjectId){
        ProfessorDTO toSave = ps.professorOneToManyMappingWithSubject(professorId, subjectId);
        return new ResponseEntity<>(toSave, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{professorId}/student/{studentId}")
    public ResponseEntity<ProfessorDTO> professorManyToManyMappingWithStudent(@PathVariable Long professorId,
                                                                              @PathVariable Long studentId){
        ProfessorDTO toSave = ps.professorManyToManyMappingWithStudent(professorId, studentId);
        return new ResponseEntity<>(toSave, HttpStatus.CREATED);
    }
}
