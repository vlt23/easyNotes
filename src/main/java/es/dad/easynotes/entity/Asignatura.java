package es.dad.easynotes.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Asignatura {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String nombre;
	private String carrera;
	@ManyToOne
	private Universidad universidad;
	private String profesores;

	@OneToMany(mappedBy = "asignatura")
	private List<Apunte> apuntes;
	
	public Asignatura() {}

	public Asignatura(String nombre, Universidad universidad, String carrera, String profesores) {
		this.nombre = nombre;
		this.carrera=carrera;
		this.universidad=universidad;
		this.profesores = profesores;
	}

	public String getNombre() {
		return nombre;
	}

}
