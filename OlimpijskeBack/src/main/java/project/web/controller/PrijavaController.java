package project.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import project.model.Prijava;
import project.service.PrijavaService;
import project.support.PrijavaDtoToPrijava;
import project.support.PrijavaToPrijavaDto;
import project.web.dto.PrijavaDTO;

@RestController
@RequestMapping(value = "/api/DODAJ", produces = MediaType.APPLICATION_JSON_VALUE)
public class PrijavaController {
	
	@Autowired
	private PrijavaService klasa3Service;
	
	@Autowired
	private PrijavaDtoToPrijava dtoToKLASA3;
	
	@Autowired 
	private PrijavaToPrijavaDto toKLASA3Dto;
	
	
	 //DODATI ANOTACIJE GDE TREBA
	
	//@PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	//@PreAuthorize("hasRole('ADMIN')")
	//@PreAuthorize("hasRole('KORISNIK')")
	
	//promeniti u security-u PUTANJE!!!
	
	//videti koja treba od dole dve metode u kom kontroleru
	
	
	
	
//	@GetMapping
//	public ResponseEntity<List<PrijavaDTO>> getAll(){
//		List<Prijava> klase = klasa3Service.findAll();
//		
//		return new ResponseEntity<>(toKLASA3Dto.convert(klase), HttpStatus.OK);
//	}
//	
//	
//	@GetMapping
//	public ResponseEntity<List<PrijavaDTO>> getAll(
//			 @RequestParam(required=false) String klasaNaziv,
//			 @RequestParam(required = false) Long klasaId,
//			 @RequestParam(value = "pageNo", defaultValue = "0") int pageNo
//			){
//		
//		Page<Prijava> page = klasa3Service.find(klasaNaziv, klasaId, pageNo);
//		
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Total-Pages", Integer.toString(page.getTotalPages()));
//        
//	    System.out.println("HEDER: " + headers);
//	    System.out.println(Integer.toString(page.getTotalPages()));
//		
//		return new ResponseEntity<>(toKLASA3Dto.convert(page.getContent()), headers, HttpStatus.OK);
//	}
//	
//	
//	@GetMapping("/{id}")
//	public ResponseEntity<PrijavaDTO> getOne(@PathVariable Long id){
//		
//		Prijava klasa3 = klasa3Service.findOne(id);
//		
//		if(klasa3 != null) {
//			return new ResponseEntity<>(toKLASA3Dto.convert(klasa3), HttpStatus.OK);
//		}else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}
//	
//
//	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<PrijavaDTO> create(@Valid @RequestBody PrijavaDTO klasa3DTO){
//		
//		Prijava klasa3 = dtoToKLASA3.convert(klasa3DTO);
//		
//		Prijava sacuvanaKlasa3 = klasa3Service.save(klasa3);
//		
//		
//		return new ResponseEntity<>(toKLASA3Dto.convert(sacuvanaKlasa3), HttpStatus.CREATED);
//	}
//	
//
//	@PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<PrijavaDTO> update(@PathVariable Long id, @Valid @RequestBody PrijavaDTO klasa3DTO){
//		
//		if(!id.equals(klasa3DTO.getId())) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//		
//		Prijava klasa3 = dtoToKLASA3.convert(klasa3DTO);
//		
//		Prijava sacuvanaKlasa3 = klasa3Service.save(klasa3);
//		
//		return new ResponseEntity<>(toKLASA3Dto.convert(sacuvanaKlasa3), HttpStatus.OK);
//	}
//	
//	
//	@DeleteMapping("/{id}")
//	public ResponseEntity<Void> delete(@PathVariable Long id){
//		
//		Prijava klasa3 = klasa3Service.findOne(id);
//				
//		if(klasa3 != null) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		
//	}
	
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
