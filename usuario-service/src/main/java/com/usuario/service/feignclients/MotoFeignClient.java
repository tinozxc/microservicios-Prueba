package com.usuario.service.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.usuario.service.modelos.MotoRequest;
import com.usuario.service.modelos.MotoResponse;

@FeignClient(name = "moto-service" , url = "http://localhost:8003" , path = "/moto")
public interface MotoFeignClient {
	
	
	@PostMapping()
	public MotoResponse saveMoto(@RequestBody MotoRequest moto);
	
	@GetMapping("/usuario/{usuarioId}")
	public List<MotoResponse> obtenerMotoPorUsuarioId(@PathVariable("usuarioId") Long usuarioId);

}
