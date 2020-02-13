package es.dad.easynotes.entity;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Asignatura {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String nombre;
	@ManyToOne
	private Carrera carrera;
	
	@ManyToOne
	private Universidad universidad;
	
	private String profesores;

	@OneToMany(mappedBy = "asignatura")
	private List<Apunte> apuntes = new ArrayList<>();
	
	public Asignatura() {}

	public Asignatura(String nombre, Universidad universidad, Carrera carrera, String profesores) {
		this.nombre = nombre;
		this.carrera=carrera;
		this.universidad=universidad;
		this.profesores = profesores;
	}

	public String getNombre() {
		return nombre;
	}

}
