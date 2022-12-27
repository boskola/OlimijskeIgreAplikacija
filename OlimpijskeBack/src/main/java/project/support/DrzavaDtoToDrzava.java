package project.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import project.model.Drzava;
import project.service.DrzavaService;
import project.web.dto.DrzavaDTO;

@Component
public class DrzavaDtoToDrzava implements Converter<DrzavaDTO, Drzava>{
	
	@Autowired
	private DrzavaService drzavaService;

	
	@Override
	public Drzava convert(DrzavaDTO dto) {
		
		Drzava drzava;
		
		if(dto.getId() == null) {
			drzava = new Drzava();
		}else {
			drzava = drzavaService.findOneById(dto.getId());
		}
		
		if(drzava != null) {
			drzava.setNaziv(dto.getNaziv());
			drzava.setOznaka(dto.getOznaka());
		}
		
		return drzava;
	}

}
