package com.mapping.homework.mapping.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "AdmissionRecord")
public class AdmissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    int fees;

    @OneToOne
    @JoinColumn(name = "student_id")
    StudentEntity student;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdmissionEntity admission = (AdmissionEntity) o;
        return getFees() == admission.getFees() && Objects.equals(getId(), admission.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFees());
    }
}
