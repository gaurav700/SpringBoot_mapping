package com.mapping.homework.mapping.controller;

import com.mapping.homework.mapping.dto.AdmissionDTO;
import com.mapping.homework.mapping.dto.StudentDTO;
import com.mapping.homework.mapping.services.AdmissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "admissions")
public class AdmissionController {
    private AdmissionService as;

    public AdmissionController(AdmissionService as) {
        this.as = as;
    }
    @GetMapping
    public ResponseEntity<List<AdmissionDTO>> getAdmissionRecord(){
        return ResponseEntity.ok(as.getAdmissionRecord());
    }

    @PostMapping
    public ResponseEntity<AdmissionDTO> saveAdmissionRecord(@RequestBody AdmissionDTO inputData){
        AdmissionDTO toSave = as.saveAdmissionRecord(inputData);
        return new ResponseEntity<>(toSave, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{admissionId}/student/{studentId}")
    public ResponseEntity<AdmissionDTO> saveMappingStudent(@PathVariable Long admissionId,
                                                           @PathVariable Long studentId){
        AdmissionDTO toSave = as.saveMappingStudent(admissionId, studentId);
        return new ResponseEntity<>(toSave, HttpStatus.CREATED);
    }

}
