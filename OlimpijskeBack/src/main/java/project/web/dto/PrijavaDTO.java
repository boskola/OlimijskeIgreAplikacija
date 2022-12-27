package project.web.dto;

import javax.validation.constraints.Pattern;

public class PrijavaDTO {
	
	private Long id;
	
	@Pattern(regexp = "^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$", message = "Datum i vreme nisu validni.")
	private String datumPrijave;
	
	private String disciplina;
	
	private Long takmicarId;
	
	private String takmicarIme;
	
	public PrijavaDTO() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDatumPrijave() {
		return datumPrijave;
	}

	public void setDatumPrijave(String datumPrijave) {
		this.datumPrijave = datumPrijave;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public Long getTakmicarId() {
		return takmicarId;
	}

	public void setTakmicarId(Long takmicarId) {
		this.takmicarId = takmicarId;
	}

	public String getTakmicarIme() {
		return takmicarIme;
	}

	public void setTakmicarIme(String takmicarIme) {
		this.takmicarIme = takmicarIme;
	}
	
	
}
