package es.dad.easynotes.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Carrera {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String nombre;
	@OneToMany(mappedBy = "carrera")
	private List<Asignatura> asignaturas= new ArrayList<>();
	
	@ManyToOne
	private Universidad universidad;

	@OneToMany(mappedBy = "carrera")
	private List<Apunte> apuntes = new ArrayList<>();
	
	public Carrera() {}
	public Carrera(String nombre) {
		this.nombre = nombre;
	}
	public Carrera(String nombre, Universidad universidad) {
		this.nombre = nombre;
		this.universidad = universidad;
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
	public List<Asignatura> getAsignaturas() {
		return asignaturas;
	}
	public void setAsignaturas(List<Asignatura> asignaturas) {
		this.asignaturas = asignaturas;
	}
	public Universidad getUniversidad() {
		return universidad;
	}
	public void setUniversidad(Universidad universidad) {
		this.universidad = universidad;
	}
	
	public List<Apunte> getApuntes() {
		return apuntes;
	}
	public void setApuntes(List<Apunte> apuntes) {
		this.apuntes = apuntes;
	}

	
}
