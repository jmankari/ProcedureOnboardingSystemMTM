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

import com.grayopus.app.models.DeficiencyChecks;
import com.grayopus.app.repositories.DeficiencyChecksRepository;

@Service("DeficiencyChecksService")
@Transactional
public class DeficiencyChecksServiceImpl implements DeficiencyChecksService {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(DeficiencyChecksServiceImpl.class);

    @Autowired
    private DeficiencyChecksRepository repository;


    @Override
    public Iterable<DeficiencyChecks> getAll() {
        return repository.findAll();
    }

    @Override
    public List<DeficiencyChecks> getAll(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging                    = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<DeficiencyChecks> pagedResult = repository.findAll(paging);

        if(pagedResult.hasContent()) {
            
            return pagedResult.getContent();
        } else {
            return new ArrayList<DeficiencyChecks>();
        }
    }

    @Override
    public Optional<DeficiencyChecks> findById(Long id) throws NullPointerException {
        return repository.findById(id);
    }

    @Override
    public DeficiencyChecks save(DeficiencyChecks deficiencyCheck) {
        return repository.save(deficiencyCheck);
    }

    @Override
    public DeficiencyChecks update(Long id, DeficiencyChecks deficiencyCheck) throws NullPointerException {
        Optional<DeficiencyChecks> o = repository.findById(id);
        if(o.isPresent())
        {
            DeficiencyChecks fromDB  = o.get();
            fromDB.setName(deficiencyCheck.getName());

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