package project.web.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.model.Drzava;
import project.model.Prijava;
import project.model.Takmicar;
import project.service.DrzavaService;
import project.service.PrijavaService;
import project.service.TakmicarService;
import project.support.TakmicarDtoToTakmicar;
import project.support.TakmicarToTakmicarDto;
import project.web.dto.ItemStatistikaDTO;
import project.web.dto.TakmicarDTO;

@RestController
@RequestMapping(value = "/api/takmicari", produces = MediaType.APPLICATION_JSON_VALUE)
public class TakmicarController {
	
	@Autowired
	private TakmicarService klasa2Service;
	
	@Autowired
	private TakmicarDtoToTakmicar dtoToKLASA2;
	
	@Autowired 
	private TakmicarToTakmicarDto toKLASA2Dto;
	
	@Autowired
	private PrijavaService prijavaService;
	
	@Autowired
	private DrzavaService drzavaService;
	
	
	 //DODATI ANOTACIJE GDE TREBA
	
	//@PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')") 
	//@PreAuthorize("hasRole('ADMIN')")
	//@PreAuthorize("hasRole('KORISNIK')")
	
	//promeniti u security-u PUTANJE!!!
	
	//videti koja treba od dole dve metode u kom kontroleru
	
	
	
	
//	@GetMapping
//	public ResponseEntity<List<TakmicarDTO>> getAll(){
//		List<Takmicar> klase = klasa2Service.findAll();
//		
//		return new ResponseEntity<>(toKLASA2Dto.convert(klase), HttpStatus.OK);
//	}
//	
//	
	
	public int ukupnoMedalja(String drzava, List<Takmicar>sviTakmicari) {
		
		int sum=0;
		
		for(Takmicar takmicar: sviTakmicari) {
			if(takmicar.getDrzava().getNaziv().equals(drzava)) {
				sum+=takmicar.getBrojMedalja();
			}
		}
		
		return sum;
	}
	
	@GetMapping("/stats")
	public ResponseEntity<List<TakmicarDTO>> getAll(){
		
		List<Takmicar> takmicari = klasa2Service.findAll();
		List<Drzava> drzave = drzavaService.findAll();
		List<Takmicar> takmicari2 = new ArrayList<>();
		
		Long i = 1L;
		
		for(Drzava drzava: drzave) {
			int sum = ukupnoMedalja(drzava.getNaziv(), takmicari);
			Takmicar t = new Takmicar();
			Drzava d = new Drzava();
			d.setNaziv(drzava.getNaziv());
			t.setBrojMedalja(sum);
			t.setId(i++);
			t.setDrzava(d);
			takmicari2.add(t);
		}
		
		return new ResponseEntity<>(toKLASA2Dto.convert(takmicari2), HttpStatus.OK);
		
	}
	
	@GetMapping("/stats2")
	public ResponseEntity<List<ItemStatistikaDTO>> statistika(){
		
		List<Takmicar> takmicari = klasa2Service.findAll();
		List<Drzava> drzave = drzavaService.findAll();
		List<ItemStatistikaDTO> statistika = new ArrayList<>();

		
		Long i = 1L;
		
		for(Drzava drzava: drzave) {
			int sum = ukupnoMedalja(drzava.getNaziv(), takmicari);
			ItemStatistikaDTO dto = new ItemStatistikaDTO(drzava.getNaziv(), sum);
			
			statistika.add(dto);
		}
		
		return new ResponseEntity<>(statistika, HttpStatus.OK);
		
	}
	

	@PostMapping(value = "/prijava/{id}/{disciplina}")
	public ResponseEntity<Void>prijava(
			@PathVariable Long id,
			@PathVariable String disciplina){
		
		
		Takmicar takmicar = klasa2Service.findOneById(id);
		
		if(takmicar==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Prijava prijava = new Prijava();
		
		prijava.setTakmicar(takmicar);
		prijava.setDisciplina(disciplina);
		prijava.setDatumPrijave(LocalDate.now().toString());
		
		prijavaService.save(prijava);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	@GetMapping
	public ResponseEntity<List<TakmicarDTO>> getAll(
			 @RequestParam(required = false) Long drzavaId,
			 @RequestParam(required=false) Integer minMedalja,
			 @RequestParam(required=false) Integer maxMedalja,
			 @RequestParam(value = "pageNo", defaultValue = "0") int pageNo
			){
		
		Page<Takmicar> page = klasa2Service.find(drzavaId, minMedalja, maxMedalja, pageNo);
		
        HttpHeaders headers = new HttpHeaders();
        headers.add("Total-Pages", Integer.toString(page.getTotalPages()));
        
	    System.out.println("HEDER: " + headers);
	    System.out.println(Integer.toString(page.getTotalPages()));
		
		return new ResponseEntity<>(toKLASA2Dto.convert(page.getContent()), headers, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<TakmicarDTO> getOne(@PathVariable Long id){
		
		Takmicar klasa2 = klasa2Service.findOneById(id);
		
		if(klasa2 != null) {
			return new ResponseEntity<>(toKLASA2Dto.convert(klasa2), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TakmicarDTO> create(@Valid @RequestBody TakmicarDTO klasa2DTO){
		
		Takmicar klasa2 = dtoToKLASA2.convert(klasa2DTO);
		
		Takmicar sacuvanaKlasa2 = klasa2Service.save(klasa2);
		
		
		return new ResponseEntity<>(toKLASA2Dto.convert(sacuvanaKlasa2), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TakmicarDTO> update(@PathVariable Long id, @Valid @RequestBody TakmicarDTO klasa2DTO){
		
		if(!id.equals(klasa2DTO.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Takmicar klasa2 = dtoToKLASA2.convert(klasa2DTO);
		
		Takmicar sacuvanaKlasa2 = klasa2Service.save(klasa2);
		
		return new ResponseEntity<>(toKLASA2Dto.convert(sacuvanaKlasa2), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		
		Takmicar klasa2 = klasa2Service.delete(id);
				
		if(klasa2 != null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	
	
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
