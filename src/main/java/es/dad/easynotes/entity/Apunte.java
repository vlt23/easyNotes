package es.dad.easynotes.entity;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Apunte {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String nombre;

	@ManyToOne
	private Asignatura asignatura;

	@ManyToOne
	private Carrera carrera;

	@ManyToOne
	private Universidad universidad;
	private LocalDateTime fechaSubida;
	
	
	@ManyToMany(cascade=CascadeType.PERSIST)
	private List<Tag> tags = new ArrayList<>();;
	private long tamanyo;
	private boolean esExamen;
	private File filePath;

	@ManyToOne
	private Usuario autor;
	
	
	//@OneToMany
	//private List<Integer> valoraciones;
	private int numeroDescargas;
	
	public Apunte() {
		this.numeroDescargas = 0;
	}
	
	public Apunte(String nombre, Asignatura asignatura, Carrera carrera, Universidad universidad,
			 File filePath, Usuario autor, LocalDateTime fechaSubida, boolean esExamen) {
		this.nombre=nombre;
		this.asignatura=asignatura;
		this.carrera=carrera;
		this.universidad = universidad;
		//this.fechaSubida = fechaSubida;
		
		//anyadimos los tags 
		tags.add(new Tag(this.nombre));
		tags.add(new Tag(this.asignatura.getNombre()));
		tags.add(new Tag(this.carrera.getNombre()));
		tags.add(new Tag(this.universidad.getNombre()));

		
		this.tamanyo = filePath.length();
		this.numeroDescargas = 0;
		this.filePath = filePath;
		this.autor = autor;
		this.fechaSubida = fechaSubida;
		this.esExamen = esExamen;
	}

	
	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public Universidad getUniversidad() {
		return universidad;
	}

	public void setUniversidad(Universidad universidad) {
		this.universidad = universidad;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public float getTamanyo() {
		return tamanyo;
	}

	public void setTamanyo(long tamanyo) {
		this.tamanyo = tamanyo;
	}

	public int getNumeroDescargas() {
		return numeroDescargas;
	}

	public void setNumeroDescargas(int numeroDescargas) {
		this.numeroDescargas = numeroDescargas;
	}
	
	public File getFilePath() {
		return filePath;
	}

	public void setFilePath(File filePath) {
		this.filePath = filePath;
	}

	public String getNombre() {
		return nombre;
	}

	public LocalDateTime getFechaSubida() {
		return fechaSubida;
	}

	public void setFechaSubida(LocalDateTime fechaSubida) {
		this.fechaSubida = fechaSubida;
	}

	public boolean isEsExamen() {
		return esExamen;
	}

	public void setEsExamen(boolean esExamen) {
		this.esExamen = esExamen;
	}

}
