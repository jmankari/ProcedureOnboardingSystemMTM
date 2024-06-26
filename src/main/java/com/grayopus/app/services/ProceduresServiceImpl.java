package com.grayopus.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.grayopus.app.models.Procedures;
import com.grayopus.app.repositories.ProceduresRepository;

@Service("ProceduresService")
@Transactional
public class ProceduresServiceImpl implements ProceduresService {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ProceduresServiceImpl.class);

	@Autowired
	private ProceduresRepository repository;
	
	
	@Override
	public Iterable<Procedures> getAll() {
		return repository.findAll();
	}

	@Override
	public List<Procedures> getAll(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging              = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Procedures> pagedResult = repository.findAll(paging);
        
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Procedures>();
        }
	}
	
	@Override
	public Optional<Procedures> findById(Long id) throws NullPointerException {
		return repository.findById(id);
	}

	@Override
	public Procedures save(Procedures procedure) {
		return repository.save(procedure);
	}

	@Override
	public Procedures update(Long id, Procedures procedure) throws NullPointerException {
		Optional<Procedures> o = repository.findById(id);
		if(o.isPresent())
		{
			Procedures fromDB  = o.get();
			fromDB.setName(procedure.getName());
			fromDB.setDescription(procedure.getDescription());
			
			return repository.save(fromDB);
		}
		else
			return null;
	}

	@Override
	public void delete(Long id) throws NullPointerException {
		repository.deleteById(id);
	}
}