package project.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import project.model.Prijava;
import project.web.dto.PrijavaDTO;


@Component
public class PrijavaToPrijavaDto implements Converter<Prijava, PrijavaDTO>{

	@Override
	public PrijavaDTO convert(Prijava prijava) {
		// TODO Auto-generated method stub
		
		PrijavaDTO dto = new PrijavaDTO();
		
		dto.setId(prijava.getId());
		dto.setDatumPrijave(prijava.getDatumPrijave());
		dto.setDisciplina(prijava.getDisciplina());
		dto.setTakmicarId(prijava.getTakmicar().getId());
		dto.setTakmicarIme(prijava.getTakmicar().getImePrezime());
		
		return dto;
	}
	
	public List<PrijavaDTO> convert(List<Prijava>klase){
		
		List<PrijavaDTO> klaseDto = new ArrayList<>();
		
		for(Prijava klasa: klase) {
			klaseDto.add(convert(klasa));
		}
		
		
		return klaseDto;
		
	}
	

}
