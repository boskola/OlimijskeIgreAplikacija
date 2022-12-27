package project.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import project.model.Drzava;
import project.web.dto.DrzavaDTO;


@Component
public class DrzavaToDrzavaDto implements Converter<Drzava, DrzavaDTO>{

	@Override
	public DrzavaDTO convert(Drzava drzava) {

		DrzavaDTO dto = new DrzavaDTO();
		
		dto.setId(drzava.getId());
		dto.setNaziv(drzava.getNaziv());
		dto.setOznaka(drzava.getOznaka());
		
		
		return dto;
	}
	
	public List<DrzavaDTO> convert(List<Drzava>klase){
		
		List<DrzavaDTO> klaseDto = new ArrayList<>();
		
		for(Drzava klasa: klase) {
			klaseDto.add(convert(klasa));
		}
		
		
		return klaseDto;
		
	}
	

}
