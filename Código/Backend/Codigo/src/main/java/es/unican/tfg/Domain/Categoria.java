package es.unican.tfg.Domain;

import javax.persistence.*;

@Entity
@Table(name = "Categoria")
public class Categoria {
	
	@Id
	private String nombre;
	
	public Categoria() {
		
	}
	
	public Categoria(String nombre) {
		super();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
