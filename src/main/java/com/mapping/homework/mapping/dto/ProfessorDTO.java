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
public class ProfessorDTO {

    Long id;

    String name;

    List<SubjectDTO> subjects;

    List<StudentDTO> students;

}
