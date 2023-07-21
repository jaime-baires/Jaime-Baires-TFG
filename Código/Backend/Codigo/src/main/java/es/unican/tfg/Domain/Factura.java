package es.unican.tfg.Domain;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "Factura")
public class Factura {
	
	@Id
    @GeneratedValue
    private Long id;
	private Date fechaFactura;
	private double total;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20, nullable=false)
	private TipoFactura tipo;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "proveedor_id")
	private Proveedor proveedor;
	
	public Factura() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaFactura() {
		return fechaFactura;
	}

	public void setFechaFactura(Date fechaFactura) {
		this.fechaFactura = fechaFactura;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public TipoFactura getTipo() {
		return tipo;
	}

	public void setTipo(TipoFactura tipo) {
		this.tipo = tipo;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

}
