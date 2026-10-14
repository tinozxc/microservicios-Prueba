package com.carro.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.carro.service.dto.CarroDto;
import com.carro.service.dto.CreateCarroDto;
import com.carro.service.entidades.Carro;

@Mapper(componentModel = "spring")
public interface CarroMapper {
	
	CarroDto ToDto (Carro carroEntity);
	
	@Mapping( target = "id" , ignore = true)
	Carro ToEntity (CreateCarroDto createCarroDto);
	
	
	List<CarroDto> toDtoList(List<Carro> carroEntity );
	

}
