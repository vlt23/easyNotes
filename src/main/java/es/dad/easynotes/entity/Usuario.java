package es.dad.easynotes.entity;

import javax.persistence.*;
import java.util.List;

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
	private boolean isAdmin;

	@OneToMany(mappedBy = "autor")
	private List<Apunte> apuntes;
	
	
	public Usuario() {}

	public Usuario(String nombre, String apellidos,int creditos, String correo, boolean isAdmin) {
		this.nombre=nombre;
		this.apellidos=apellidos;
		this.correo=correo;
		this.creditos=creditos;
		this.isAdmin = isAdmin;
		this.baneado= false;
		this.numeroDescargas=0;
	}
	
}
