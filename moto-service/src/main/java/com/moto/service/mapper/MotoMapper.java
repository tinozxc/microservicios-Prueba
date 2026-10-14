package com.moto.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.moto.service.dto.CreateMotoDto;
import com.moto.service.dto.MotoDto;
import com.moto.service.entidades.Moto;

@Mapper(componentModel = "spring")
public interface MotoMapper {

	MotoDto toDto (Moto motoEntity );
	
	
	@Mapping(target = "id" , ignore = true)
	Moto toEntity (CreateMotoDto createMotoDto);
	
	
	List<MotoDto> toDtoList(List<Moto> motoEntity);
	
	
	
}
