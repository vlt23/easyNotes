package es.dad.easynotes.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity

public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String nombre;
	private String apellidos;
	private String correo;
	private int creditos;
	private int numeroDescargas;
	private boolean baneado;
	private boolean administrador;
	
	
	public Usuario() {}
	public Usuario(String nombre, String apellidos,int creditos, String correo, boolean adminsitrador) {
		this.nombre=nombre;
		this.apellidos=apellidos;
		this.correo=correo;
		this.creditos=creditos;
		this.administrador = administrador;
		this.baneado= false;
		this.numeroDescargas=0;
	}
	
}
