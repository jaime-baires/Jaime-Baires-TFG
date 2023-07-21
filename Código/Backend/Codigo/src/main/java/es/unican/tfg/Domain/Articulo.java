package es.unican.tfg.Domain;

import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Articulo")
public class Articulo {
	
	@Id
    @GeneratedValue
    private Long id;
	private String descripcion;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date fechaAlta;
	private double precio;
	private double stock;
	private double stockSeguridad;
	private double descuento;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20, nullable=false)
	private Unidad unidad;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20, nullable=false)
	private TipoIVA IVA;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoria_id", nullable=false)
	private Categoria categoria;
	
	public Articulo() {
		
	}

	public TipoIVA getIva() {
		return IVA;
	}

	public void setIva(TipoIVA iva) {
		this.IVA = iva;
	}

	public Unidad getUnidad() {
		return unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public double getStock() {
		return stock;
	}
	public void setStock(double stock) {
		this.stock = stock;
	}
	public double getStockSeguridad() {
		return stockSeguridad;
	}
	public void setStockSeguridad(double stockSeguridad) {
		this.stockSeguridad = stockSeguridad;
	}
	public double getDescuento() {
		return descuento;
	}
	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

}
