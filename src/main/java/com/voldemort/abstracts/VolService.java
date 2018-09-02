package com.voldemort.abstracts;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

public abstract class VolService<DTO> {
	public abstract DTO save(DTO dto);

    /**
     * Get all.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
	public abstract Page<DTO> findAll(Pageable pageable);


    /**
     * Get the "id" .
     *
     * @param id the id of the entity
     * @return the entity
     */
	public abstract DTO findOne(Long id);

    /**
     * Delete the "id" .
     *
     * @param id the id of the entity
     */
	public abstract void delete(Long id);
}
