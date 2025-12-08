package org.fmm.communitymgmt;

import javax.sql.DataSource;

import org.fmm.communitymgmt.common.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import jakarta.annotation.PostConstruct;

//@Component
public class DataSourceChecker {
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private PersonRepository personRepository;
	
	@PostConstruct
	public void checkDataSource() {
		DataSource dataSource = applicationContext.getBean(DataSource.class);
		
		if (dataSource == null) {
			System.out.println("DataSource bean not found");
		} else {
			System.out.println("DataSource bean is available: " + dataSource);
		}
		
		if (personRepository != null) {
			System.out.println("PersonRepository bean is available: " + personRepository);
		} else {
			System.out.println("PersonRepository bean not found");
		}
	}
}
