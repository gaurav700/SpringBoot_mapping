package com.mapping.homework.mapping.repositories;

import com.mapping.homework.mapping.entities.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {
}
