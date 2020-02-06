package es.dad.easynotes.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity

public class Apunte {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String asignatura;
	private String carrera;
	private String universidad;
	//private LocalDateTime fechaSubida;
	
	//@OneToMany
	private ArrayList<String> tags;
	private float tamanyo;
	
	//@OneToMany
	//private List<Integer> valoraciones;
	private int numeroDescargas;
	
	public Apunte() {}
	
	public Apunte(String asignatura, String carrera, String universidad, 
			ArrayList<String> tags, float tamanyo) {
		
		this.asignatura=asignatura;
		this.carrera=carrera;
		this.universidad = universidad;
		//this.fechaSubida = fechaSubida;
		
		
		if(tags==null) {		//inicializamos la lista de tags
			this.tags = new ArrayList<>();
		}else {
			this.tags = tags;
		}	
		//this.tags.addAll("asignatura, carrera, universidad");
		this.tags.add(asignatura);
		this.tags.add(carrera);
		this.tags.add(universidad);
		
		this.tamanyo = tamanyo;
		this.numeroDescargas = 0;
		//this.valoraciones = new ArrayList<Integer>();
		
	}

	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	public String getUniversidad() {
		return universidad;
	}

	public void setUniversidad(String universidad) {
		this.universidad = universidad;
	}

	public ArrayList<String> getTags() {
		return tags;
	}

	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}

	public float getTamanyo() {
		return tamanyo;
	}

	public void setTamanyo(float tamanyo) {
		this.tamanyo = tamanyo;
	}

	public int getNumeroDescargas() {
		return numeroDescargas;
	}

	public void setNumeroDescargas(int numeroDescargas) {
		this.numeroDescargas = numeroDescargas;
	}
	

	
	
	
}
