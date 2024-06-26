package com.grayopus.app.controllers;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grayopus.app.services.DeficiencyChecksServiceImpl;
import com.grayopus.app.models.DeficiencyChecks;

@RestController
@RequestMapping("/ProcedureOnboardingSystemMTM/DeficiencyChecks")
@CrossOrigin
public class DeficiencyChecksController {

    private final Logger LOGGER = LoggerFactory.getLogger(DeficiencyChecksController.class);

    @Autowired
    private DeficiencyChecksServiceImpl service;

    @GetMapping
    public Iterable<DeficiencyChecks> getAll() {
        Iterable<DeficiencyChecks> itb = service.getAll();
        Long size                      = itb.spliterator().estimateSize();
        LOGGER.info("Size of Iterable:"+size);
        return itb;
    }

    //can pass as both PathVariable and RequestParam
    //Below commented is for path variables type
    //@GetMapping("/{pageNo}/{pageSize}/{sortBy}")
    @GetMapping("/PaginationandSorting")
    public ResponseEntity<List<DeficiencyChecks>> getAll(
            //@PathVariable(value = "pageNo") Integer pageNo,
            //@PathVariable(value = "pageSize") Integer pageSize,
            //@PathVariable(value = "sortBy") String sortBy){
            @RequestParam(defaultValue = "1", value = "pageNo") Integer pageNo,
            @RequestParam(defaultValue = "10", value = "pageSize")  Integer pageSize,
            @RequestParam(defaultValue = "id", value = "sortBy")  String sortBy){

        List<DeficiencyChecks> list = service.getAll(pageNo, pageSize, sortBy);
        int size             = list.size();
        LOGGER.info("Size of List is:"+size);

        return new ResponseEntity<List<DeficiencyChecks>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeficiencyChecks> getById(@PathVariable(value = "id") Long id) throws NullPointerException
    {
        DeficiencyChecks def = service.findById(id)
                .orElseThrow(() -> new NullPointerException());
        LOGGER.info("ID to be displayed is:"+id);
        return ResponseEntity.ok().body(def);
    }

    @PostMapping("/add")
    public DeficiencyChecks add(@Valid @RequestBody DeficiencyChecks deficiencyCheck) throws ParseException {

        DeficiencyChecks savedE = service.save(deficiencyCheck);
        LOGGER.info("Saved successfully ID:"+savedE.getId());
        return savedE;
    }

    @PutMapping("/{id}")
    public DeficiencyChecks update(@RequestBody @PathVariable(value = "id") Long id,
                            @Valid @RequestBody DeficiencyChecks deficiencyCheck) throws NullPointerException, ParseException {

        DeficiencyChecks savedE = service.update(id, deficiencyCheck);
        return savedE;
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> delete(@PathVariable("id") Long id) throws NullPointerException {

        service.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted successfully, DeficiencyChecks ID:"+id, Boolean.TRUE);
        LOGGER.info("Deleted successfully, DeficiencyChecks ID:"+id);
        return response;
    }
}