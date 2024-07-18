package com.mapping.homework.mapping.services;

import com.mapping.homework.mapping.dto.ProfessorDTO;
import com.mapping.homework.mapping.entities.ProfessorEntity;
import com.mapping.homework.mapping.entities.StudentEntity;
import com.mapping.homework.mapping.entities.SubjectEntity;
import com.mapping.homework.mapping.repositories.ProfessorRepository;
import com.mapping.homework.mapping.repositories.StudentRepository;
import com.mapping.homework.mapping.repositories.SubjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfessorService {

    private ProfessorRepository professorRepository;
    private SubjectRepository subjectRepository;
    private StudentRepository studentRepository;
    private ModelMapper mm;

    public ProfessorService(ProfessorRepository pr, SubjectRepository sr, StudentRepository studentRepository, ModelMapper mm) {
        this.professorRepository = pr;
        this.subjectRepository = sr;
        this.studentRepository = studentRepository;
        this.mm = mm;
    }

    public ProfessorDTO saveProfessor(ProfessorDTO inputData) {
        ProfessorEntity toSave = mm.map(inputData, ProfessorEntity.class);
        ProfessorEntity data = professorRepository.save(toSave);
        return mm.map(data, ProfessorDTO.class);
    }

    public List<ProfessorDTO> getProfessor() {
        List<ProfessorEntity> data = professorRepository.findAll();
        return data.stream().map(d-> mm.map(d, ProfessorDTO.class)).collect(Collectors.toList());
    }

    public ProfessorDTO professorOneToManyMappingWithSubject(Long professorId, Long subjectId) {
        Optional<ProfessorEntity> professorEntity = professorRepository.findById(professorId);
        Optional<SubjectEntity> subjectEntity = subjectRepository.findById(subjectId);
            if (professorEntity.isPresent() && subjectEntity.isPresent()) {
            ProfessorEntity professor = professorEntity.get();
            SubjectEntity subject = subjectEntity.get();

            subject.setProfessor(professor);
            subjectRepository.save(subject);

            professor.getSubjects().add(subject);
            professorRepository.save(professor);

            return mm.map(professor, ProfessorDTO.class);
        }else{
            throw new EntityNotFoundException("Professor or subject not found");
        }
    }

    public ProfessorDTO professorManyToManyMappingWithStudent(Long professorId, Long studentId) {
        Optional<ProfessorEntity> professorEntity = professorRepository.findById(professorId);
        Optional<StudentEntity> studentEntity = studentRepository.findById(studentId);
        if (professorEntity.isPresent() && studentEntity.isPresent()) {
            ProfessorEntity professor = professorEntity.get();
            StudentEntity student = studentEntity.get();

            professor.getStudents().add(student);
            student.getProfessors().add(professor);

            professorRepository.save(professor);
            studentRepository.save(student);

            return mm.map(professor, ProfessorDTO.class);
        } else {
            throw new EntityNotFoundException("Professor or student not found");
        }
    }

}
