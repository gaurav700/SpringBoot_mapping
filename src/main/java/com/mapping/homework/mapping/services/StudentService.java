package com.mapping.homework.mapping.services;

import com.mapping.homework.mapping.dto.StudentDTO;
import com.mapping.homework.mapping.entities.AdmissionEntity;
import com.mapping.homework.mapping.entities.ProfessorEntity;
import com.mapping.homework.mapping.entities.StudentEntity;
import com.mapping.homework.mapping.entities.SubjectEntity;
import com.mapping.homework.mapping.repositories.AdmissionRepository;
import com.mapping.homework.mapping.repositories.ProfessorRepository;
import com.mapping.homework.mapping.repositories.StudentRepository;
import com.mapping.homework.mapping.repositories.SubjectRepository;
import io.micrometer.observation.ObservationTextPublisher;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.validator.internal.constraintvalidators.bv.time.futureorpresent.FutureOrPresentValidatorForOffsetTime;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private StudentRepository studentRepository;
    private ProfessorRepository professorRepository;
    private SubjectRepository subjectRepository;
    private AdmissionRepository admissionRepository;
    private ModelMapper mm;

    public StudentService(StudentRepository sr, ProfessorRepository professorRepository, SubjectRepository subjectRepository, AdmissionRepository admissionRepository, ModelMapper mm) {
        this.studentRepository = sr;
        this.professorRepository = professorRepository;
        this.subjectRepository = subjectRepository;
        this.admissionRepository = admissionRepository;
        this.mm = mm;
    }

    public StudentDTO saveStudent(StudentDTO inputData) {
        StudentEntity toSave = mm.map(inputData, StudentEntity.class);
        StudentEntity data = studentRepository.save(toSave);
        return mm.map(data, StudentDTO.class);
    }

    public List<StudentDTO> getStudent() {
        List<StudentEntity> data = studentRepository.findAll();
        return data.stream().map(d->mm.map(d, StudentDTO.class)).collect(Collectors.toList());
    }

    public StudentDTO studentManyToManyMappingToProfessor(Long studentId, Long professorId) {
        Optional<ProfessorEntity> professorEntity = professorRepository.findById(professorId);
        Optional<StudentEntity> studentEntity = studentRepository.findById(studentId);

        if(professorEntity.isPresent() && studentEntity.isPresent()){
            ProfessorEntity professor = professorEntity.get();
            StudentEntity student = studentEntity.get();

            professor.getStudents().add(student);
            student.getProfessors().add(professor);

            professorRepository.save(professor);
            studentRepository.save(student);

            return mm.map(student, StudentDTO.class);
        }else{
            throw new EntityNotFoundException("Professor or Student not found");
        }
    }

    public StudentDTO studentManyToManyMappingToSubject(Long studentId, Long subjectId) {
        Optional<StudentEntity> studentEntity = studentRepository.findById(studentId);
        Optional<SubjectEntity> subjectEntity = subjectRepository.findById(subjectId);

        if(studentEntity.isPresent() && subjectEntity.isPresent()){
            StudentEntity student = studentEntity.get();
            SubjectEntity subject = subjectEntity.get();

            student.getSubjects().add(subject);
            subject.getStudents().add(student);

            studentRepository.save(student);
            subjectRepository.save(subject);

            return mm.map(student, StudentDTO.class);
        }else{
            throw new EntityNotFoundException("Subject or Student not found");
        }
    }

    public StudentDTO studentOneToOneMappingToAdmission(Long studentId, Long admissionId) {
        Optional<AdmissionEntity> admissionEntity = admissionRepository.findById(admissionId);
        Optional<StudentEntity> studentEntity = studentRepository.findById(studentId);

        if(admissionEntity.isPresent() && studentEntity.isPresent()){
            AdmissionEntity admission = admissionEntity.get();
            StudentEntity student = studentEntity.get();

            student.setStudentAdmission(admission);
            studentRepository.save(student);

            return mm.map(student , StudentDTO.class);
        }else{
            throw new EntityNotFoundException("Admission or Student not found");
        }
    }
}
