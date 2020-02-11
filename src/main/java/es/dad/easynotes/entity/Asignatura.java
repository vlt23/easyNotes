package es.dad.easynotes.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Asignatura {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String asignatura;
	private String carrera;
	private String universidad;
	private String profesores;
	
	public Asignatura() {}
	public Asignatura(String nombre, String universidad, String carrera, String profesores) {
		this.asignatura=nombre;
		this.carrera=carrera;
		this.universidad=universidad;
		this.profesores = profesores;
	}
}
