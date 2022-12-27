package project.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Takmicar {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, name="ime_prezime")
	private String imePrezime;							//izmeni po potrebi zbog fronta-baze
	
	@Column(name="broj_medalja")
	private int brojMedalja;
	
	@Column(nullable = false, name="datum_rodjenja")
	private String datumRodjenja;
	
	@ManyToOne
	private Drzava drzava;
	
	@OneToMany(mappedBy = "takmicar", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Prijava> prijave = new ArrayList<>();
	
	public Takmicar() {}
	
	public List<Prijava> getPrijave() {
		return prijave;
	}

	public void dodajPrijava(Prijava prijava) {
		this.prijave.add(prijava);
		if(!equals(prijava.getTakmicar())) {
			prijava.setTakmicar(this);
		}
	}

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

	public Drzava getDrzava() {
		return drzava;
	}

	public void setDrzava(Drzava drzava) {
		this.drzava = drzava;
		if(drzava != null && !drzava.getTakmicari().contains(this)) {
			drzava.getTakmicari().add(this);
		}
	}
	
	
}
