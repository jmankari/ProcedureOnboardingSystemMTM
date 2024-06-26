package com.grayopus.app.services;

import java.util.List;
import java.util.Optional;

import com.grayopus.app.models.Documents;

public interface DocumentsService {

    Iterable<Documents> getAll();

    List<Documents> getAll(Integer pageNo, Integer pageSize, String sortBy);

    Optional<Documents> findById(Long id);

    Documents save(Documents document);

    Documents update(Long id, Documents document);

    void delete(Long id);
}