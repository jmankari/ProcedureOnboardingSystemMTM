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
@Table(name="procedures")
@Getter
@Setter
@NoArgsConstructor
public class Procedures {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String name;
	
	private String description;
	
	@ManyToMany
    @JoinTable(name = "procedure_document", 
    		   joinColumns = @JoinColumn(name = "procedure_id"),
    		   inverseJoinColumns = @JoinColumn(name = "document_id"))
	private List<Documents> documents;
}