package com.carro.service.controlador;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.carro.service.dto.CarroDto;
import com.carro.service.dto.CreateCarroDto;
import com.carro.service.entidades.Carro;
import com.carro.service.service.CarroService;

@RestController
@RequestMapping("/carro")
public class CarroController {

	@Autowired
	private CarroService carroService;
	
	@GetMapping
	public ResponseEntity<List<CarroDto>> listarCarro(){ 
		List<CarroDto> carroDtoList = carroService.getAll();
		if(carroDtoList.isEmpty()) { 
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(carroDtoList);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<CarroDto> obtenerCarro(@PathVariable Long id){ 
		return ResponseEntity.ok(carroService.getCarroById(id));
	}
	
	@PostMapping
	public ResponseEntity<CarroDto> crearCarro(@RequestBody CreateCarroDto carroDtoCreate){ 
		 CarroDto carroDto = carroService.crearCarroDto(carroDtoCreate);
		 
		 URI location = ServletUriComponentsBuilder
				 .fromCurrentRequest()
				 .path("/{id}")
				 .buildAndExpand(carroDto.getId())
				 .toUri();
		 
		 return ResponseEntity.created(location).body(carroDto);
	}
	
	 @GetMapping("/usuario/{usuarioId}")
	 public ResponseEntity<List<CarroDto> > listarCarroPorUsuarioId(@PathVariable("usuarioId") Long id){ 
		 
		 List<CarroDto> listCarro = carroService.listarPorUsuario(id);
		 
		 if(! listCarro.isEmpty()) { 
			 return ResponseEntity.ok(listCarro);
		 }
		 
		 return ResponseEntity.notFound().build();
		
	 }
	
}
