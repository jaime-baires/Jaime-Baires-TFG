package es.unican.tfg.Domain;

import javax.persistence.*;

@Entity
@Table(name = "Rol")
public class Rol {
	
	@Id
	private String nombre;
	
	public Rol() {
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
