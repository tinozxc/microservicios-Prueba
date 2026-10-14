package com.moto.service.controlador;

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

import com.moto.service.dto.CreateMotoDto;
import com.moto.service.dto.MotoDto;
import com.moto.service.service.MotoService;



@RestController
@RequestMapping("/moto")
public class MotoController {

	@Autowired
	private MotoService motoService;
	
	@GetMapping
	public ResponseEntity<List<MotoDto>> listarMoto(){ 
		List<MotoDto> carroDtoList = motoService.listarMoto();
		if(carroDtoList.isEmpty()) { 
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(carroDtoList);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<MotoDto> obtenerMoto(@PathVariable Long id){ 
		return ResponseEntity.ok(motoService.obtenerMotoPorId(id));
	}
	
	@PostMapping
	public ResponseEntity<MotoDto> crearMoto(@RequestBody CreateMotoDto motoCreateDto){ 
		 MotoDto MotoDto = motoService.crearUnaMoto(motoCreateDto);
		 
		 URI location = ServletUriComponentsBuilder
				 .fromCurrentRequest()
				 .path("/{id}")
				 .buildAndExpand(MotoDto.getId())
				 .toUri();
		 
		 return ResponseEntity.created(location).body(MotoDto);
	}
	
	 @GetMapping("/usuario/{usuarioId}")
	 public ResponseEntity<List<MotoDto> > listarMotoPorUsuarioId(@PathVariable("usuarioId") Long id){ 
		 
		 List<MotoDto> listMoto = motoService.obtenerPorUsuarioId(id);
		 
		 if(! listMoto.isEmpty()) { 
			 return ResponseEntity.ok(listMoto);
		 }
		 
		 return ResponseEntity.notFound().build();
		
	 }
	
}
