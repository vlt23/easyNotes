package es.dad.easynotes.entity;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Universidad {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String nombre;

	@OneToMany(mappedBy = "universidad")
	private List<Asignatura> asignaturas= new ArrayList<>();
	
	@OneToMany(mappedBy = "universidad")
	private List<Carrera> carreras= new ArrayList<>();

	@OneToMany(mappedBy = "universidad")
	private List<Apunte> apuntes= new ArrayList<>();
	
	public Universidad() {}
	public Universidad(String nombre ) {
		this.nombre = nombre;
	
	}
	
	
	
	
	public List<Asignatura> getAsignaturas() {
		return asignaturas;
	}
	public void setAsignaturas(List<Asignatura> asignaturas) {
		this.asignaturas = asignaturas;
	}
	public List<Carrera> getCarreras() {
		return carreras;
	}
	public void setCarreras(List<Carrera> carreras) {
		this.carreras = carreras;
	}
	public List<Apunte> getApuntes() {
		return apuntes;
	}
	public void setApuntes(List<Apunte> apuntes) {
		this.apuntes = apuntes;
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

	
	
}
