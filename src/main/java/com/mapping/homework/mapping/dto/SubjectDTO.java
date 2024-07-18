package com.mapping.homework.mapping.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDTO {
    Long id;

    String title;

    ProfessorDTO professor;

    List<StudentDTO> students;

}
