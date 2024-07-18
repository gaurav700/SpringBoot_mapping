package com.mapping.homework.mapping.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdmissionDTO {

    Long id;

    int fees;

    StudentDTO student;

}
