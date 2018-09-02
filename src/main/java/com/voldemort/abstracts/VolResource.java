package com.voldemort.abstracts;

import java.net.URISyntaxException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import com.voldemort.common.Result;

public abstract class VolResource<DTO> {

	/**
	 * POST /entities : Create a new Entity.
	 *
	 * @param DTO
	 *            the DTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         DTO, or with status 400 (Bad Request) if the entity has already
	 *         an ID
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	public abstract Result<DTO> create(DTO dto) throws URISyntaxException;

	/**
	 * PUT /entities : Updates an existing entity.
	 *
	 * @param DTO
	 *            the DTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         DTO, or with status 400 (Bad Request) if the DTO is not
	 *         valid, or with status 500 (Internal Server Error) if the DTO
	 *         couldn't be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	public abstract Result<DTO> update(DTO dto) throws URISyntaxException;

	/**
	 * GET /entities : get all the entities.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of entities in
	 *         body
	 */
	public abstract Result<Page<DTO>> getAll(Pageable pageable);

	/**
	 * GET /entities/:id : get the "id" entity.
	 *
	 * @param id
	 *            the id of the DTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the DTO,
	 *         or with status 404 (Not Found)
	 */
	public abstract Result<DTO> get(Long id);

	/**
	 * DELETE /entities/:id : delete the "id" entity.
	 *
	 * @param id
	 *            the id of the DTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	public abstract Result<Void> delete(Long id);

}