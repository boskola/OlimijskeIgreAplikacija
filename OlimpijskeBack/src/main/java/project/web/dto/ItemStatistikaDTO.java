package project.web.dto;

public class ItemStatistikaDTO {
	
	private String drzava;
	
	private int brojMedalja;
	
	public ItemStatistikaDTO() {}

	public ItemStatistikaDTO(String drzava, int brojMedalja) {
		super();
		this.drzava = drzava;
		this.brojMedalja = brojMedalja;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	public int getBrojMedalja() {
		return brojMedalja;
	}

	public void setBrojMedalja(int brojMedalja) {
		this.brojMedalja = brojMedalja;
	}
	
	

}
