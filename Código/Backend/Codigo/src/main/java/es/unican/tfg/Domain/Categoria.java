package es.unican.tfg.Domain;

import javax.persistence.*;

@Entity
@Table(name = "Categoria")
public class Categoria {
	
	@Id
    @GeneratedValue
    private Long id;
	private String nombre;
	
	public Categoria() {
		
	}
	
	public Categoria(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
