package com.voldemort.abstracts;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class VolMapper<E, D> {

	public abstract D toDTO(E entity);
	public abstract E toEtity(D userDTO);
	public abstract E entityFromId(Long id);

	public List<D> toDTOs(List<E> entityList){
		return entityList.stream()
				.filter(Objects::nonNull)
				.map(this::toDTO)
				.collect(Collectors.toList());
	}
	public List<E> toEntities(List<D> dtoList){
		return dtoList.stream()
				.filter(Objects::nonNull)
				.map(this::toEtity)
				.collect(Collectors.toList());
	}
}