package project.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.model.Drzava;
import project.service.DrzavaService;
import project.support.DrzavaDtoToDrzava;
import project.support.DrzavaToDrzavaDto;
import project.web.dto.DrzavaDTO;

@RestController
@RequestMapping(value = "/api/drzave", produces = MediaType.APPLICATION_JSON_VALUE)
public class DrzavaController {
	
	@Autowired
	private DrzavaService klasa1Service;
	
	@Autowired
	private DrzavaDtoToDrzava dtoToKLASA1;
	
	@Autowired 
	private DrzavaToDrzavaDto toKLASA1Dto;
	
	
	 //DODATI ANOTACIJE GDE TREBA
	
	//@PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	//@PreAuthorize("hasRole('ADMIN')")
	//@PreAuthorize("hasRole('KORISNIK')")
	
	//promeniti u security-u PUTANJE!!!
	
	//videti koja treba od dole dve metode u kom kontroleru
	
	
	
	@PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	@GetMapping
	public ResponseEntity<List<DrzavaDTO>> getAll(){
		List<Drzava> klase = klasa1Service.findAll();
		
		return new ResponseEntity<>(toKLASA1Dto.convert(klase), HttpStatus.OK);
	}
//	
//	
//	@GetMapping
//	public ResponseEntity<List<DrzavaDTO>> getAll(
//			 @RequestParam(required=false) String klasaNaziv,
//			 @RequestParam(required = false) Long klasaId,
//			 @RequestParam(value = "pageNo", defaultValue = "0") int pageNo
//			){
//		
//		Page<Drzava> page = klasa1Service.find(klasaNaziv, klasaId, pageNo);
//		
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Total-Pages", Integer.toString(page.getTotalPages()));
//        
//	    System.out.println("HEDER: " + headers);
//	    System.out.println(Integer.toString(page.getTotalPages()));
//		
//		return new ResponseEntity<>(toKLASA1Dto.convert(page.getContent()), headers, HttpStatus.OK);
//	}
//	
//	
//	@GetMapping("/{id}")
//	public ResponseEntity<DrzavaDTO> getOne(@PathVariable Long id){
//		
//		Drzava klasa1 = klasa1Service.findOne(id);
//		
//		if(klasa1 != null) {
//			return new ResponseEntity<>(toKLASA1Dto.convert(klasa1), HttpStatus.OK);
//		}else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}
//	
//
//	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<DrzavaDTO> create(@Valid @RequestBody DrzavaDTO klasa1DTO){
//		
//		Drzava klasa1 = dtoToKLASA1.convert(klasa1DTO);
//		
//		Drzava sacuvanaKlasa1 = klasa1Service.save(klasa1);
//		
//		
//		return new ResponseEntity<>(toKLASA1Dto.convert(sacuvanaKlasa1), HttpStatus.CREATED);
//	}
//	
//
//	@PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<DrzavaDTO> update(@PathVariable Long id, @Valid @RequestBody DrzavaDTO klasa1DTO){
//		
//		if(!id.equals(klasa1DTO.getId())) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//		
//		Drzava klasa1 = dtoToKLASA1.convert(klasa1DTO);
//		
//		Drzava sacuvanaKlasa1 = klasa1Service.save(klasa1);
//		
//		return new ResponseEntity<>(toKLASA1Dto.convert(sacuvanaKlasa1), HttpStatus.OK);
//	}
//	
//	
//	@DeleteMapping("/{id}")
//	public ResponseEntity<Void> delete(@PathVariable Long id){
//		
//		Drzava klasa1 = klasa1Service.findOne(id);
//				
//		if(klasa1 != null) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		
//	}
	
	
	//za slucaj da zatreba localdatetime citanje
	
//	private LocalDateTime getLocalDateTime(String datumIVreme) throws DateTimeParseException {
//	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//	        LocalDate datum = LocalDate.parse(datumIVreme.substring(0, 10), formatter);
//	        LocalTime vreme = LocalTime.parse(datumIVreme.substring(11), DateTimeFormatter.ofPattern("HH:mm"));
//	        return LocalDateTime.of(datum, vreme);
//	}
	
	
	
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
