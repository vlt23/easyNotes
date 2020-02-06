package objects;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity

public class Apunte {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String asignatura;
	private String carrera;
	private String universidad;
	private LocalDateTime fechaSubida;
	private List<String> tags;
	private float tamanyo;
	private List<Integer> valoraciones;
	private int numeroDescargas;
	
	public Apunte(String asignatura, String carrera, String universidad, LocalDateTime fechaSubida, 
			ArrayList<String> tags, float tamanyo) {
		
		this.asignatura=asignatura;
		this.carrera=carrera;
		this.universidad = universidad;
		this.fechaSubida = fechaSubida;
		
		
		if(tags==null) {		//inicializamos la lista de tags
			this.tags = new ArrayList<>();
		}else {
			this.tags = tags;
		}	
		this.tags.add(asignatura);
		this.tags.add(carrera);
		this.tags.add(universidad);
		
		this.tamanyo = tamanyo;
		this.numeroDescargas = 0;
		this.valoraciones = new ArrayList<Integer>();
		
	}
	

	
}
