package com.mapping.homework.mapping.services;

import com.mapping.homework.mapping.dto.SubjectDTO;
import com.mapping.homework.mapping.entities.ProfessorEntity;
import com.mapping.homework.mapping.entities.StudentEntity;
import com.mapping.homework.mapping.entities.SubjectEntity;
import com.mapping.homework.mapping.repositories.ProfessorRepository;
import com.mapping.homework.mapping.repositories.StudentRepository;
import com.mapping.homework.mapping.repositories.SubjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubjectService {

    private SubjectRepository subjectRepository;
    private ProfessorRepository professorRepository;
    private StudentRepository studentRepository;
    private ModelMapper mm;

    public SubjectService(SubjectRepository sr, ProfessorRepository professorRepository, StudentRepository studentRepository, ModelMapper mm) {
        this.subjectRepository = sr;
        this.professorRepository = professorRepository;
        this.studentRepository = studentRepository;
        this.mm = mm;
    }

    public SubjectDTO saveSubject(SubjectDTO inputData) {
        SubjectEntity toSave = mm.map(inputData, SubjectEntity.class);
        SubjectEntity data = subjectRepository.save(toSave);
        return mm.map(data, SubjectDTO.class);
    }

    public List<SubjectDTO> getSubject() {
        List<SubjectEntity> data = subjectRepository.findAll();
        return data.stream().map(d -> mm.map(d, SubjectDTO.class)).collect(Collectors.toList());
    }

    public SubjectDTO subjectManyToOneMappingToProfessor(Long subjectId, Long professorId) {
        Optional<SubjectEntity> subjectEntity = subjectRepository.findById(subjectId);
        Optional<ProfessorEntity> professorEntity = professorRepository.findById(professorId);

        if(subjectEntity.isPresent() && professorEntity.isPresent()){
            ProfessorEntity professor = professorEntity.get();
            SubjectEntity subject = subjectEntity.get();

            professor.getSubjects().add(subject);
            subject.setProfessor(professor);

            professorRepository.save(professor);
            subjectRepository.save(subject);

            return mm.map(subject, SubjectDTO.class);
        }else{
            throw new EntityNotFoundException("Professor or Subject not found");
        }
    }


    public SubjectDTO subjectManyToManyMappingToStudent(Long subjectId, Long studentId) {
        Optional<SubjectEntity> subjectEntity = subjectRepository.findById(subjectId);
        Optional<StudentEntity> studentEntity = studentRepository.findById(studentId);

        if(subjectEntity.isPresent() && studentEntity.isPresent()){
            StudentEntity student = studentEntity.get();
            SubjectEntity subject = subjectEntity.get();

            student.getSubjects().add(subject);
            subject.getStudents().add(student);

            studentRepository.save(student);
            subjectRepository.save(subject);

            return mm.map(subject, SubjectDTO.class);
        }else{
            throw new EntityNotFoundException("Subject or Student not found");
        }
    }
}
