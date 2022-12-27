package project.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import project.model.Takmicar;

public interface TakmicarService {
	
	Takmicar findOneById(Long id);
	Takmicar save(Takmicar klasa2);
	Takmicar update(Takmicar klasa2);
	Takmicar delete(Long id);
	
	List<Takmicar> findAll();
	Page<Takmicar> findAll(Pageable pageable);
	Page<Takmicar>findAll(Integer pageNo);
	
	Page<Takmicar> find(Long drzavaId, Integer minMedalja, Integer maxMedalja, Integer pageNo);
	

}
