package es.dad.easynotes.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Tag {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String tag;
	
	@ManyToMany
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
	
	
	
}
