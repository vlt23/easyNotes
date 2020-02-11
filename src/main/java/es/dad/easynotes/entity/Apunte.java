package es.dad.easynotes.entity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity

public class Apunte {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne
	private Asignatura asignatura;

	@ManyToOne
	private Carrera carrera;

	@ManyToOne
	private Universidad universidad;
	//private LocalDateTime fechaSubida;
	
	@ManyToMany(mappedBy = "apuntes")
	private ArrayList<String> tags;
	private long tamanyo;

	private File file;

	@ManyToOne
	private Usuario autor;
	
	//@OneToMany
	//private List<Integer> valoraciones;
	private int numeroDescargas;
	
	public Apunte() {
		this.numeroDescargas = 0;
	}
	
	public Apunte(Asignatura asignatura, Carrera carrera, Universidad universidad,
			ArrayList<String> tags, File file/*, Usuario autor*/) {  // TODO
		
		this.asignatura=asignatura;
		this.carrera=carrera;
		this.universidad = universidad;
		//this.fechaSubida = fechaSubida;
		
		
		if(tags==null) {		//inicializamos la lista de tags
			this.tags = new ArrayList<>();
		}else {
			this.tags = tags;
		}	
		//this.tags.addAll("asignatura, carrera, universidad");
		//this.tags.add(asignatura.getNombre());
		//this.tags.add(carrera);
		//this.tags.add(universidad.getNombre());
		
		this.tamanyo = file.length();
		this.numeroDescargas = 0;
		//this.valoraciones = new ArrayList<Integer>();
		this.file = file;
		//this.autor = autor;  // TODO
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

	public List<String> getTags() {
		return tags;
	}

	public void setTags(ArrayList<String> tags) {
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
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
