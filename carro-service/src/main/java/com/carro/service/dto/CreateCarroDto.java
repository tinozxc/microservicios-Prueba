package com.carro.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class CreateCarroDto {

	private String marca;
	
	private String modelo;
	
	private Long usuarioId;

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public CreateCarroDto(String marca, String modelo, Long usuarioId) {
		
		this.marca = marca;
		this.modelo = modelo;
		this.usuarioId = usuarioId;
	}
	
	public CreateCarroDto() {}
	
}
