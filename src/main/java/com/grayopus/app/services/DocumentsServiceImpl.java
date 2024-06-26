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

import com.grayopus.app.models.Documents;
import com.grayopus.app.repositories.DocumentsRepository;

@Service("DocumentsService")
@Transactional
public class DocumentsServiceImpl implements DocumentsService {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ProceduresServiceImpl.class);

    @Autowired
    private DocumentsRepository repository;


    @Override
    public Iterable<Documents> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Documents> getAll(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging              = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Documents> pagedResult = repository.findAll(paging);

        if(pagedResult.hasContent()) {
            
            return pagedResult.getContent();
        } else {
            return new ArrayList<Documents>();
        }
    }

    @Override
    public Optional<Documents> findById(Long id) throws NullPointerException {
        return repository.findById(id);
    }

    @Override
    public Documents save(Documents document) {
        return repository.save(document);
    }

    @Override
    public Documents update(Long id, Documents document) throws NullPointerException {
        Optional<Documents> o = repository.findById(id);
        if(o.isPresent())
        {
            Documents fromDB  = o.get();
            fromDB.setName(document.getName());
            fromDB.setType(document.getType());

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