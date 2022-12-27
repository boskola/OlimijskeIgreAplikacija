package project.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import project.model.Prijava;
import project.service.PrijavaService;
import project.service.TakmicarService;
import project.web.dto.PrijavaDTO;

@Component
public class PrijavaDtoToPrijava implements Converter<PrijavaDTO, Prijava>{
	
	@Autowired
	private PrijavaService prijavaService;
	
	@Autowired
	private TakmicarService takmicarService;
	
	@Override
	public Prijava convert(PrijavaDTO dto) {
		
		Prijava prijava;
		
		if(dto.getId()== null) {
			prijava = new Prijava();
		}else {
			prijava = prijavaService.findOneById(dto.getId());
		}
		
		if(prijava!=null) {
			prijava.setDisciplina(dto.getDisciplina());
			prijava.setDatumPrijave(dto.getDatumPrijave());
			prijava.setTakmicar(takmicarService.findOneById(dto.getTakmicarId()));
		}
		
		return prijava;
	}

}
