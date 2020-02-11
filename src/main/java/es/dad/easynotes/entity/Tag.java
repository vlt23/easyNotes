package es.dad.easynotes.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tag {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String tag;
	public Tag() {}
	public Tag(String tag) {
		this.tag=tag;
	}
	
	
	
}
