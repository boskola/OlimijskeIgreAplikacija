package project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Prijava {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, name="datum_prijave")
	private String datumPrijave;
	
	@Column
	private String disciplina;		
	
	@ManyToOne
	private Takmicar takmicar;
	
	public Prijava() {}

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

	public Takmicar getTakmicar() {
		return takmicar;
	}

	public void setTakmicar(Takmicar takmicar) {
		this.takmicar = takmicar;
		if(takmicar != null && !takmicar.getPrijave().contains(this)) {
			takmicar.getPrijave().add(this);
		}
	}
	
	
	

}
