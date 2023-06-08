package es.unican.tfg.Domain;

import javax.persistence.*;

@Entity
@Table(name = "LineaFactura")
public class LineaFactura {
	
	@Id
    @GeneratedValue
    private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "factura_id", nullable=false)
	private Factura factura;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "articulo_id", nullable=false)
	private Articulo articulo;
	
	private double cantidad;
	private double precio;
	private double descuento;
	private double importe;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20, nullable=false)
	private TipoIVA iva;

	public LineaFactura() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public TipoIVA getIva() {
		return iva;
	}

	public void setIva(TipoIVA iva) {
		this.iva = iva;
	}	

}
