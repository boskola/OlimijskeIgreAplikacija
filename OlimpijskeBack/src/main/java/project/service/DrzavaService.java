package project.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import project.model.Drzava;

public interface DrzavaService {
	
	Drzava findOneById(Long id);
//	Drzava save(Drzava klasa1);
//	Drzava update(Drzava klasa1);
//	Drzava delete(Long id);
//	
	List<Drzava> findAll();
//	Page<Drzava> findAll(Pageable pageable);
//	Page<Drzava>findAll(Integer pageNo);
	
	//Page<Drzava> find(Long prostorId, Integer minGodina, Integer maxGodina, Integer pageNo);
	
}
