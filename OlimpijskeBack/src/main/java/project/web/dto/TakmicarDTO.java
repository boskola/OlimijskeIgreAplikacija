package project.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

public class TakmicarDTO {
	
	private Long id;
	
	@NotBlank
	@Length(max = 60)
	private String imePrezime;
	
	@Positive
	private int brojMedalja;
	
	@Pattern(regexp = "^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$", message = "Datum i vreme nisu validni.")
	private String datumRodjenja;
	
	private Long drzavaId;
	
	private String drzavaNaziv;
	
	public TakmicarDTO() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImePrezime() {
		return imePrezime;
	}

	public void setImePrezime(String imePrezime) {
		this.imePrezime = imePrezime;
	}

	public int getBrojMedalja() {
		return brojMedalja;
	}

	public void setBrojMedalja(int brojMedalja) {
		this.brojMedalja = brojMedalja;
	}

	public String getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(String datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public Long getDrzavaId() {
		return drzavaId;
	}

	public void setDrzavaId(Long drzavaId) {
		this.drzavaId = drzavaId;
	}

	public String getDrzavaNaziv() {
		return drzavaNaziv;
	}

	public void setDrzavaNaziv(String drzavaNaziv) {
		this.drzavaNaziv = drzavaNaziv;
	}
	
	
	

}
