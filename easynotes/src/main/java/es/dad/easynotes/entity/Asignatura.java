package es.dad.easynotes.entity;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.DataSerializable;

import javax.persistence.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Asignatura implements DataSerializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String nombre;

	@ManyToOne
	private Carrera carrera;
	
	@ManyToOne
	private Universidad universidad;
	
	private String profesores;

	@OneToMany(mappedBy = "asignatura")
	private List<Apunte> apuntes = new ArrayList<>();
	
	public Asignatura() {}

	public Asignatura(String nombre, Universidad universidad, Carrera carrera, String profesores) {
		this.nombre = nombre;
		this.carrera = carrera;
		this.universidad = universidad;
		this.profesores = profesores;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public void writeData(ObjectDataOutput objectDataOutput) throws IOException {
		objectDataOutput.writeLong(id);
		objectDataOutput.writeUTF(nombre);
	}

	@Override
	public void readData(ObjectDataInput objectDataInput) throws IOException {
		id = objectDataInput.readLong();
		nombre = objectDataInput.readUTF();
	}

}
