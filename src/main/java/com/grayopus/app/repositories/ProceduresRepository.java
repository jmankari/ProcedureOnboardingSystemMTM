package com.grayopus.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grayopus.app.models.Procedures;

@Repository
public interface ProceduresRepository extends JpaRepository<Procedures, Long>{	
}