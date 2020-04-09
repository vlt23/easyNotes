package es.dad.easynotes.entity;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.DataSerializable;

import javax.persistence.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Apunte implements DataSerializable {

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

	private int numeroDescargas;
	
	public Apunte() {
		this.numeroDescargas = 0;
	}
	
	public Apunte(String nombre, Asignatura asignatura, Carrera carrera, Universidad universidad,
			 File filePath, Usuario autor, LocalDateTime fechaSubida, boolean esExamen) {
		this.nombre = nombre;
		this.asignatura = asignatura;
		this.carrera = carrera;
		this.universidad = universidad;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Apunte apunte = (Apunte) o;
		return id == apunte.id &&
				tamanyo == apunte.tamanyo &&
				esExamen == apunte.esExamen &&
				nombre.equals(apunte.nombre) &&
				asignatura.equals(apunte.asignatura) &&
				carrera.equals(apunte.carrera) &&
				universidad.equals(apunte.universidad) &&
				fechaSubida.equals(apunte.fechaSubida) &&
				tags.equals(apunte.tags) &&
				filePath.equals(apunte.filePath) &&
				autor.equals(apunte.autor);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre, asignatura, carrera, universidad, fechaSubida, tags,
				tamanyo, esExamen, filePath, autor);
	}

	@Override
	public void writeData(ObjectDataOutput objectDataOutput) throws IOException {
		objectDataOutput.writeLong(this.id);
		objectDataOutput.writeUTF(this.nombre);
		objectDataOutput.writeObject(asignatura);
		objectDataOutput.writeObject(carrera);
		objectDataOutput.writeObject(universidad);
		objectDataOutput.writeObject(fechaSubida);
		int nTags = tags.size();
		objectDataOutput.writeInt(nTags);
		for (Tag tag : tags) {
			objectDataOutput.writeObject(tag);
		}
		objectDataOutput.writeLong(tamanyo);
		objectDataOutput.writeBoolean(esExamen);
		objectDataOutput.writeObject(filePath);
		objectDataOutput.writeObject(autor);
	}

	@Override
	public void readData(ObjectDataInput objectDataInput) throws IOException {
		id = objectDataInput.readLong();
		nombre = objectDataInput.readUTF();
		asignatura = objectDataInput.readObject();
		carrera = objectDataInput.readObject();
		universidad = objectDataInput.readObject();
		fechaSubida = objectDataInput.readObject();
		int nTags = objectDataInput.readInt();
		for (int i = 0; i < nTags; i++) {
			tags.add(objectDataInput.readObject());
		}
		tamanyo = objectDataInput.readLong();
		esExamen = objectDataInput.readBoolean();
		filePath = objectDataInput.readObject();
		autor = objectDataInput.readObject();
	}

}
