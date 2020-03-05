package es.dad.easynotes.entity;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String nick;
	private String passwordHash;
	@ElementCollection(fetch = FetchType.EAGER)
	 private List<String> roles;
	
	private String nombre;
	private String apellidos;
	private String correo;
	private int creditos;
	private int numeroDescargas;
	private boolean isAdmin;

	@OneToMany(mappedBy = "autor")
	private List<Apunte> apuntes;

	public Usuario() {}

	public Usuario (String nick, String passwd, String... roles) {
		this.nick = nick;
		this.passwordHash = passwd;
		this.roles = new ArrayList<>(Arrays.asList(roles));
	}

	public Usuario(String nombre, String apellidos, int creditos, String correo, boolean isAdmin) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.correo = correo;
		this.creditos = creditos;
		this.isAdmin = isAdmin;
		this.numeroDescargas = 0;
	}

	/**
	 * Constructor para registrar
	 * @param nick nick
	 * @param passwordHash password hashed
	 * @param nombre name
	 * @param apellidos surname
	 * @param correo email
	 */
	public Usuario(String nick, String passwordHash, String nombre, String apellidos, String correo) {
		this.nick = nick;
		this.passwordHash = passwordHash;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.correo = correo;
		this.creditos = 20;
		this.isAdmin = false;
		this.numeroDescargas = 0;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public int getCreditos() {
		return creditos;
	}

	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}

	public int getNumeroDescargas() {
		return numeroDescargas;
	}

	public void setNumeroDescargas(int numeroDescargas) {
		this.numeroDescargas = numeroDescargas;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public List<Apunte> getApuntes() {
		return apuntes;
	}

	public void setApuntes(List<Apunte> apuntes) {
		this.apuntes = apuntes;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Cuando sube un apunte, se le suman 10 creditos
	 */
	public void increaseCreditos() {
		creditos += 10;
	}

	/**
	 * Cuando descarga un apunte, se le restan 10 creditos
	 */
	public void decreaseCreditos() {
		creditos -= 10;
	}

	public void increaseNumeroDescargas() {
		numeroDescargas += 1;
	}

	@Override
	public int hashCode() {
		return Long.hashCode(this.id);
	}

	@Override
	public boolean equals(Object o) {
		if (o.getClass() != null && this.getClass() != o.getClass()) {
			return false;
		}
		Usuario usuario = (Usuario) o;
		return this.id == usuario.id;
	}

}
