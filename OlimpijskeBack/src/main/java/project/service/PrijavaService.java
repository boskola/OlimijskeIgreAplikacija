package project.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import project.model.Prijava;

public interface PrijavaService {
	
	Prijava findOneById(Long id);
	Prijava save(Prijava klasa3);
//	Prijava update(Prijava klasa3);
//	Prijava delete(Long id);
//	
	List<Prijava> findAll();
//	Page<Prijava> findAll(Pageable pageable);
//	Page<Prijava>findAll(Integer pageNo);
	
	//Page<Prijava> find(Long prostorId, Integer minGodina, Integer maxGodina, Integer pageNo);
	

}
