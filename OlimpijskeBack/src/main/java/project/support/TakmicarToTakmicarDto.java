package project.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import project.model.Takmicar;
import project.web.dto.TakmicarDTO;


@Component
public class TakmicarToTakmicarDto implements Converter<Takmicar, TakmicarDTO>{

	@Override
	public TakmicarDTO convert(Takmicar takmicar) {
		TakmicarDTO dto = new TakmicarDTO();
		
		dto.setId(takmicar.getId());
		dto.setBrojMedalja(takmicar.getBrojMedalja());
		dto.setImePrezime(takmicar.getImePrezime());
		dto.setDatumRodjenja(takmicar.getDatumRodjenja());
		dto.setDrzavaId(takmicar.getDrzava().getId());
		dto.setDrzavaNaziv(takmicar.getDrzava().getNaziv());
		
		return dto;
	}
	
	public List<TakmicarDTO> convert(List<Takmicar>klase){
		
		List<TakmicarDTO> klaseDto = new ArrayList<>();
		
		for(Takmicar klasa: klase) {
			klaseDto.add(convert(klasa));
		}
		
		
		return klaseDto;
		
	}
	

}
