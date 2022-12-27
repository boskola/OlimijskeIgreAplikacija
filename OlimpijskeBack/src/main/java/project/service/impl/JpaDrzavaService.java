package project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.model.Drzava;
import project.repository.DrzavaRepository;
import project.service.DrzavaService;

@Service
public class JpaDrzavaService implements DrzavaService {
	
	@Autowired
	private DrzavaRepository drzavaRepository;
	
	@Override
	public Drzava findOneById(Long id) {
		// TODO Auto-generated method stub
		return drzavaRepository.getOne(id);
	}

	@Override
	public List<Drzava> findAll() {
		// TODO Auto-generated method stub
		return drzavaRepository.findAll();
	}

}
