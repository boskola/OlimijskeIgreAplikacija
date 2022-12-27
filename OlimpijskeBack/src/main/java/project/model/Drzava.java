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
import javax.persistence.OneToMany;

@Entity
public class Drzava {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String naziv;	
	
	@Column(unique = true)
	private String oznaka;				//troslovno SRB, USA i sl.
	
	@OneToMany(mappedBy = "drzava", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Takmicar> takmicari = new ArrayList<>();
	
	public Drzava() {}
	
	

	public List<Takmicar> getTakmicari() {
		return takmicari;
	}

	public void dodajTakmicar(Takmicar takmicar) {
		this.takmicari.add(takmicar);
		if(!equals(takmicar.getDrzava())) {
			takmicar.setDrzava(this);
		}
	}

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
