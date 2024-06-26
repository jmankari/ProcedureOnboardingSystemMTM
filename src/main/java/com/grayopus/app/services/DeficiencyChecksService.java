package com.grayopus.app.services;

import java.util.List;
import java.util.Optional;

import com.grayopus.app.models.DeficiencyChecks;

public interface DeficiencyChecksService {

    Iterable<DeficiencyChecks> getAll();

    List<DeficiencyChecks> getAll(Integer pageNo, Integer pageSize, String sortBy);

    Optional<DeficiencyChecks> findById(Long id);

    DeficiencyChecks save(DeficiencyChecks deficiencyCheck);

    DeficiencyChecks update(Long id, DeficiencyChecks deficiencyCheck);

    void delete(Long id);
}