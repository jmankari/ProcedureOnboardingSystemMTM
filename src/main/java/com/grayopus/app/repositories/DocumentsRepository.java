package com.grayopus.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grayopus.app.models.Documents;

@Repository
public interface DocumentsRepository extends JpaRepository<Documents, Long>{
}