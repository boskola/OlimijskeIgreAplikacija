package project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import project.model.Prijava;
import project.model.Takmicar;
import project.repository.TakmicarRepository;
import project.service.TakmicarService;

@Service
public class JpaTakmicarService implements TakmicarService {
	
	@Autowired
	private TakmicarRepository takmicarRepository;
	
	@Override
	public Takmicar findOneById(Long id) {
		// TODO Auto-generated method stub
		return takmicarRepository.getOne(id);
	}

	@Override
	public Takmicar save(Takmicar klasa2) {
		// TODO Auto-generated method stub
		return takmicarRepository.save(klasa2);
	}

	@Override
	public Takmicar update(Takmicar klasa2) {
		// TODO Auto-generated method stub
		return takmicarRepository.save(klasa2);
	}

	@Override
	public Takmicar delete(Long id) {
		Takmicar takmicar = findOneById(id);
		
		if(takmicar != null) {
			takmicar.getDrzava().getTakmicari().remove(takmicar);
			for(Prijava prijava: takmicar.getPrijave()) {
				if(prijava.getTakmicar().getId()==id) {
					prijava.setTakmicar(null);
				}
			}
		}
			takmicarRepository.delete(takmicar);
			return takmicar;
	}

	@Override
	public List<Takmicar> findAll() {
		// TODO Auto-generated method stub
		return takmicarRepository.findAll();
	}

	@Override
	public Page<Takmicar> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return takmicarRepository.findAll(pageable);
	}

	@Override
	public Page<Takmicar> findAll(Integer pageNo) {
		// TODO Auto-generated method stub
		return takmicarRepository.findAll(PageRequest.of(pageNo,3));
	}

	@Override
	public Page<Takmicar> find(Long drzavaId, Integer minMedalja, Integer maxMedalja, Integer pageNo) {
		
		if(minMedalja==null) {
			minMedalja=Integer.MIN_VALUE;
		}
		
		if(maxMedalja==null) {
			maxMedalja=Integer.MAX_VALUE;
		}
		
		if(drzavaId==null) {
			return takmicarRepository.findByBrojMedaljaBetween(minMedalja, maxMedalja, PageRequest.of(pageNo,3));
		}else {
			return takmicarRepository.findByDrzavaIdAndBrojMedaljaBetween(drzavaId, minMedalja, maxMedalja, PageRequest.of(pageNo,3));
		}
		
	}

}
