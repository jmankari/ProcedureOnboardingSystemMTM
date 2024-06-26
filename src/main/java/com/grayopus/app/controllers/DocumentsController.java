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

import com.grayopus.app.services.DocumentsServiceImpl;
import com.grayopus.app.models.Documents;

@RestController
@RequestMapping("/ProcedureOnboardingSystemMTM/Documents")
@CrossOrigin
public class DocumentsController {

    private final Logger LOGGER = LoggerFactory.getLogger(DocumentsController.class);

    @Autowired
    private DocumentsServiceImpl service;

    @GetMapping
    public Iterable<Documents> getAll() {
        Iterable<Documents> itb = service.getAll();
        Long size               = itb.spliterator().estimateSize();
        LOGGER.info("Size of Iterable:"+size);
        return itb;
    }

    //can pass as both PathVariable and RequestParam
    //Below commented is for path variables type
    //@GetMapping("/{pageNo}/{pageSize}/{sortBy}")
    @GetMapping("/PaginationandSorting")
    public ResponseEntity<List<Documents>> getAll(
            //@PathVariable(value = "pageNo") Integer pageNo,
            //@PathVariable(value = "pageSize") Integer pageSize,
            //@PathVariable(value = "sortBy") String sortBy){
            @RequestParam(defaultValue = "1", value = "pageNo") Integer pageNo,
            @RequestParam(defaultValue = "10", value = "pageSize")  Integer pageSize,
            @RequestParam(defaultValue = "id", value = "sortBy")  String sortBy){

        List<Documents> list = service.getAll(pageNo, pageSize, sortBy);
        int size             = list.size();
        LOGGER.info("Size of List is:"+size);

        return new ResponseEntity<List<Documents>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Documents> getById(@PathVariable(value = "id") Long id) throws NullPointerException
    {
        Documents doc = service.findById(id)
                .orElseThrow(() -> new NullPointerException());
        LOGGER.info("ID to be displayed is:"+id);
        return ResponseEntity.ok().body(doc);
    }

    @PostMapping("/add")
    public Documents add(@Valid @RequestBody Documents document) throws ParseException {

        Documents savedE = service.save(document);
        
        LOGGER.info("Saved successfully ID:"+savedE.getId());
        return savedE;
    }

    @PutMapping("/{id}")
    public Documents update(@RequestBody @PathVariable(value = "id") Long id,
                             @Valid @RequestBody Documents document) throws NullPointerException, ParseException {

        Documents savedE = service.update(id, document);
        
        return savedE;
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> delete(@PathVariable("id") Long id) throws NullPointerException {

        service.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted successfully, Documents ID:"+id, Boolean.TRUE);
        LOGGER.info("Deleted successfully, Documents ID:"+id);
        return response;
    }
}