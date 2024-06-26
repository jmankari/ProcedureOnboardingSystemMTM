package com.grayopus.app.services;

import java.util.List;
import java.util.Optional;

import com.grayopus.app.models.Procedures;

public interface ProceduresService {

	Iterable<Procedures> getAll();
	
	List<Procedures> getAll(Integer pageNo, Integer pageSize, String sortBy);
	
	Optional<Procedures> findById(Long id);
	
	Procedures save(Procedures procedure);

	Procedures update(Long id, Procedures procedure);

	void delete(Long id);	
}