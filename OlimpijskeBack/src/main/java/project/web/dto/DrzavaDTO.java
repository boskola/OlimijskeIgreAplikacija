package project.web.dto;

public class DrzavaDTO {
	
private Long id;
	
	private String naziv;
	
	private String oznaka;
	
	public DrzavaDTO() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}
	


}


//Ako zatreba...

//@NotBlank(message = "Datum i vreme nisu zadati.")
//@Pattern(regexp = "^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1]) (2[0-3]|[01][0-9]):[0-5][0-9]$", message = "Datum i vreme nisu validni.")
//private String datumIVreme;