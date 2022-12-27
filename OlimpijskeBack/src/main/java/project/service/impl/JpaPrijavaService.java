package project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.model.Prijava;
import project.repository.PrijavaRepository;
import project.service.PrijavaService;

@Service
public class JpaPrijavaService implements PrijavaService {
	
	@Autowired
	private PrijavaRepository prijavaRepository;
	
	@Override
	public Prijava findOneById(Long id) {
		// TODO Auto-generated method stub
		return prijavaRepository.getOne(id);
	}

	@Override
	public List<Prijava> findAll() {
		// TODO Auto-generated method stub
		return prijavaRepository.findAll();
	}

	@Override
	public Prijava save(Prijava klasa3) {
		// TODO Auto-generated method stub
		return prijavaRepository.save(klasa3);
	}

}
