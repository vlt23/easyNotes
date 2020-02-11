package es.dad.easynotes.entity;

import java.util.ArrayList;

public class Examen extends Apunte {
	private String profesor;
	
	public Examen() {super();}
	public Examen(String asignatura, String carrera, String universidad, 
			ArrayList<String> tags, float tamanyo, String profesor) {
		
		super(asignatura, carrera, universidad, tags, tamanyo);
		this.profesor=profesor;
		
	}
	public String getProfesor() {
		return profesor;
	}
	public void setProfesor(String profesor) {
		this.profesor = profesor;
	}
	
	
}
