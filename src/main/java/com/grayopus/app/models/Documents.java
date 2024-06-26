package com.grayopus.app.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="documents")
@Getter
@Setter
@NoArgsConstructor
public class Documents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;

    private String type;
    
    @ManyToMany
    @JoinTable(name = "document_deficiencycheck", 
    		   joinColumns = @JoinColumn(name = "document_id"),
    		   inverseJoinColumns = @JoinColumn(name = "deficiencycheck_id"))
    private List<DeficiencyChecks> deficiencyChecks;
}