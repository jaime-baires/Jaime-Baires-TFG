package es.unican.tfg.Controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import es.unican.tfg.Domain.*;
import es.unican.tfg.Repository.ProveedorRepository;

@RestController
@RequestMapping("/proveedores")
public class ProveedoresController {
	
	private final ProveedorRepository proveedorRepository;

	public ProveedoresController(ProveedorRepository proveedorRepository) {
		this.proveedorRepository = proveedorRepository;
	}
	
	@GetMapping
    public List<Proveedor> getProveedores() {
        return proveedorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> getProveedor(@PathVariable Long id) {
    	Proveedor proveedor;
    	try {
    		proveedor = proveedorRepository.findById(id).get();
    	} catch (Exception e) {
    		return ResponseEntity.status(404).build();
    	}
        return ResponseEntity.ok(proveedor);
    }
    
    @PostMapping
    public ResponseEntity<Proveedor> createProveedor(@RequestBody Proveedor proveedor) throws URISyntaxException {
    	Proveedor proveedorGuardado = proveedorRepository.save(proveedor);
        return ResponseEntity.created(new URI("/proveedores/" + proveedorGuardado.getId())).body(proveedorGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> updateProveedor(@PathVariable Long id, @RequestBody Proveedor proveedor) {
    	Proveedor currentProveedor;
    	try {
    		currentProveedor = proveedorRepository.findById(id).get();
    	} catch (Exception e) {
    		return ResponseEntity.status(404).build();
    	}
    	currentProveedor.setNombre(proveedor.getNombre());
    	currentProveedor.setFechaAlta(proveedor.getFechaAlta());
    	currentProveedor.setNIF(proveedor.getNIF());
    	currentProveedor.setDireccion(proveedor.getDireccion());
    	currentProveedor.setTelefono(proveedor.getTelefono());
    	currentProveedor.setEmail(proveedor.getEmail());
    	currentProveedor.setActivo(proveedor.isActivo());
    	currentProveedor = proveedorRepository.save(proveedor);

        return ResponseEntity.ok(currentProveedor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProveedor(@PathVariable Long id) {
    	try {
    		Proveedor proveedor = proveedorRepository.findById(id).get();
    	} catch (Exception e) {
    		return ResponseEntity.status(400).build();
    	}
    	proveedorRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
