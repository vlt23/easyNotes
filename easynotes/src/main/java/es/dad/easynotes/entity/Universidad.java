package es.dad.easynotes.entity;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.DataSerializable;

import javax.persistence.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Universidad implements DataSerializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String nombre;

	@OneToMany(mappedBy = "universidad")
	private List<Asignatura> asignaturas = new ArrayList<>();
	
	@OneToMany(mappedBy = "universidad")
	private List<Carrera> carreras = new ArrayList<>();

	@OneToMany(mappedBy = "universidad")
	private List<Apunte> apuntes = new ArrayList<>();
	
	public Universidad() {}

	public Universidad(String nombre ) {
		this.nombre = nombre;
	}

	public List<Asignatura> getAsignaturas() {
		return asignaturas;
	}
	public void setAsignaturas(List<Asignatura> asignaturas) {
		this.asignaturas = asignaturas;
	}
	public List<Carrera> getCarreras() {
		return carreras;
	}
	public void setCarreras(List<Carrera> carreras) {
		this.carreras = carreras;
	}
	public List<Apunte> getApuntes() {
		return apuntes;
	}
	public void setApuntes(List<Apunte> apuntes) {
		this.apuntes = apuntes;
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
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
