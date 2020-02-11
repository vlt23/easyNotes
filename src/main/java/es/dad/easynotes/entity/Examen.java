package es.dad.easynotes.entity;

import java.io.File;
import java.util.ArrayList;

public class Examen extends Apunte {
	private String profesor;
	
	public Examen() {super();}
	public Examen(Asignatura asignatura, String carrera, Universidad universidad,
			ArrayList<String> tags, float tamanyo, String profesor) {
		
		//super(asignatura, carrera, universidad, tags, new File("/home/valen/zswap.sh"));
		this.profesor=profesor;
		
	}
	public String getProfesor() {
		return profesor;
	}
	public void setProfesor(String profesor) {
		this.profesor = profesor;
	}
	
	
}
