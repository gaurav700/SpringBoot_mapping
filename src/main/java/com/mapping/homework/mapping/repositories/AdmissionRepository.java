package com.mapping.homework.mapping.repositories;

import com.mapping.homework.mapping.entities.AdmissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmissionRepository extends JpaRepository<AdmissionEntity, Long> {
}
