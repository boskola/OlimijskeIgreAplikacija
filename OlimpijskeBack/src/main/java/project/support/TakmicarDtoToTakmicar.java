package project.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import project.model.Takmicar;
import project.service.DrzavaService;
import project.service.PrijavaService;
import project.service.TakmicarService;
import project.web.dto.TakmicarDTO;

@Component
public class TakmicarDtoToTakmicar implements Converter<TakmicarDTO, Takmicar>{
	
	@Autowired
	private TakmicarService takmicarService;
	
	@Autowired
	private DrzavaService drzavaService;


	@Override
	public Takmicar convert(TakmicarDTO dto) {
		Takmicar takmicar;
		
		if(dto.getId() == null) {
			takmicar = new Takmicar();
		}else {
			takmicar = takmicarService.findOneById(dto.getId());
		}
		
		if(takmicar!=null) {
			takmicar.setImePrezime(dto.getImePrezime());
			takmicar.setBrojMedalja(dto.getBrojMedalja());
			takmicar.setDatumRodjenja(dto.getDatumRodjenja());
			takmicar.setDrzava(drzavaService.findOneById(dto.getDrzavaId()));
		}
		
		return takmicar;
	}

}
