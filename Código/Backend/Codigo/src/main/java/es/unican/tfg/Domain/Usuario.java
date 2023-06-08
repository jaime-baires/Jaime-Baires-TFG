package es.unican.tfg.Domain;

import javax.persistence.Column;
import javax.persistence.*;

@Entity
@Table(name = "Usuario")
public class Usuario {
	
	@Id
    @GeneratedValue
    private Long id;
	private String nombre;
	
	@Column(unique = true)
	private String usuario;
	
	private String contraseña;
	private boolean esAdmin;
	
	public Usuario() {

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

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public boolean isEsAdmin() {
		return esAdmin;
	}

	public void setEsAdmin(boolean esAdmin) {
		this.esAdmin = esAdmin;
	}

}
