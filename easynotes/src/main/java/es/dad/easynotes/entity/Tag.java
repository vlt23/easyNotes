package es.dad.easynotes.entity;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.DataSerializable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Tag implements DataSerializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String tag;
	
	@ManyToMany(mappedBy = "tags")
	private List<Apunte> apuntes = new ArrayList<>();;
	
	public Tag() {}
	public Tag(String tag) {
		this.tag=tag;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public List<Apunte> getApuntes() {
		return apuntes;
	}
	public void setApuntes(List<Apunte> apuntes) {
		this.apuntes = apuntes;
	}

	@Override
	public void writeData(ObjectDataOutput objectDataOutput) throws IOException {
		objectDataOutput.writeLong(id);
		objectDataOutput.writeUTF(tag);
	}

	@Override
	public void readData(ObjectDataInput objectDataInput) throws IOException {
		id = objectDataInput.readLong();
		tag = objectDataInput.readUTF();
	}

}
