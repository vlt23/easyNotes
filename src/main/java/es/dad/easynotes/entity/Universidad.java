package es.dad.easynotes.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Universidad {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String nombre;
	private String campus;
	
	public Universidad() {}
	public Universidad(String nombre, String campus ) {
		this.nombre = nombre;
		this.campus = campus;
	}
	
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCampus() {
		return campus;
	}
	public void setCampus(String campus) {
		this.campus = campus;
	}
	
	
}
