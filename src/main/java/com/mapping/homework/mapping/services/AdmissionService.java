package com.mapping.homework.mapping.services;

import com.mapping.homework.mapping.dto.AdmissionDTO;
import com.mapping.homework.mapping.entities.AdmissionEntity;
import com.mapping.homework.mapping.entities.StudentEntity;
import com.mapping.homework.mapping.repositories.AdmissionRepository;
import com.mapping.homework.mapping.repositories.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdmissionService {

    private AdmissionRepository admissionRepository;
    private StudentRepository studentRepository;
    private ModelMapper mm;

    public AdmissionService(AdmissionRepository ar, StudentRepository sr, ModelMapper mm) {
        this.admissionRepository = ar;
        this.studentRepository = sr;
        this.mm = mm;
    }

    public AdmissionDTO saveAdmissionRecord(AdmissionDTO inputData) {
        AdmissionEntity toSave = mm.map(inputData, AdmissionEntity.class);
        AdmissionEntity data = admissionRepository.save(toSave);
        return mm.map(data, AdmissionDTO.class);
    }

    public List<AdmissionDTO> getAdmissionRecord() {
        List<AdmissionEntity> data = admissionRepository.findAll();
        return data.stream().map(d-> mm.map(d, AdmissionDTO.class)).collect(Collectors.toList());
    }

    public AdmissionDTO saveMappingStudent(Long admissionId, Long studentId) {
        Optional<AdmissionEntity> admissionEntity = admissionRepository.findById(admissionId);
        Optional<StudentEntity> studentEntity = studentRepository.findById(studentId);
        if(admissionEntity.isPresent() && studentEntity.isPresent()){
            AdmissionEntity admission = admissionEntity.get();
            admission.setStudent(studentEntity.get());
            AdmissionEntity data = admissionRepository.save(admission);
            return mm.map(data, AdmissionDTO.class);
        }else{
            throw new EntityNotFoundException("Admission or Student not found");
        }
    }
}
