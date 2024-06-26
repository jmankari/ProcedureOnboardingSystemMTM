package com.grayopus.app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.grayopus.app.commonincludes.*;
import com.grayopus.app.models.*;
import com.grayopus.app.controllers.*;

@Component
class DemoCommandLineRunner implements CommandLineRunner
{
	private static final Logger LOGGER = LoggerFactory.getLogger(DemoCommandLineRunner.class);
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String ddlauto;
	
	@Autowired
	private DeficiencyChecksController dcController;
	
	@Autowired
	private DocumentsController docController;
	
	@Autowired
	private ProceduresController proController;
	
	
	@Override
	public void run(String... args)throws Exception
	{
		
		if(ddlauto.equals(Constant.Create) || ddlauto.equals(Constant.Create_Drop))
		{
			DeficiencyChecks dc1 = new DeficiencyChecks();
			dc1.setName("DeficiencyCheck1");
			dcController.add(dc1);
			
			DeficiencyChecks dc2 = new DeficiencyChecks();
			dc2.setName("DeficiencyCheck2");
			dcController.add(dc2);
			
			DeficiencyChecks dc3 = new DeficiencyChecks();
			dc3.setName("DeficiencyCheck3");
			dcController.add(dc3);
			List<DeficiencyChecks> dcList1 = new ArrayList<DeficiencyChecks>();
			dcList1.add(dc1);
			dcList1.add(dc2);
			dcList1.add(dc3);
			
			DeficiencyChecks dc4 = new DeficiencyChecks();
			dc4.setName("DeficiencyCheck4");
			dcController.add(dc4);
			
			DeficiencyChecks dc5 = new DeficiencyChecks();
			dc5.setName("DeficiencyCheck5");
			dcController.add(dc5);
			
			DeficiencyChecks dc6 = new DeficiencyChecks();
			dc6.setName("DeficiencyCheck6");
			dcController.add(dc6);
			
			List<DeficiencyChecks> dcList2 = new ArrayList<DeficiencyChecks>();
			dcList2.add(dc4);
			dcList2.add(dc5);
			dcList2.add(dc6);
			
			Documents doc1 = new Documents();
			doc1.setName("doc1");
			doc1.setType("type1");
			doc1.setDeficiencyChecks(dcList1);
			docController.add(doc1);
			
			Documents doc2 = new Documents();
			doc2.setName("doc2");
			doc2.setType("type2");
			doc2.setDeficiencyChecks(dcList2);
			docController.add(doc2);
			
			Procedures pro = new Procedures();
			pro.setName("pro1");
			pro.setDescription("sample");
			List<Documents> docList = new ArrayList<Documents>();
			docList.add(doc1);
            docList.add(doc2);
            pro.setDocuments(docList);
            proController.add(pro);
			
		}
	}
}