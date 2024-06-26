package com.grayopus.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grayopus.app.models.DeficiencyChecks;

@Repository
public interface DeficiencyChecksRepository extends JpaRepository<DeficiencyChecks, Long>{
}