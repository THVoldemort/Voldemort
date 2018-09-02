package com.voldemort.rest;

import java.net.URISyntaxException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.voldemort.abstracts.VolResource;
import com.voldemort.common.Result;
import com.voldemort.common.ResultCode;
import com.voldemort.common.ResultMessage;
import com.voldemort.dto.UserDTO;
import com.voldemort.service.UserService;


@RestController
@RequestMapping("/user")
public class UserResource extends VolResource<UserDTO>{

	@Autowired
	private UserService userService;
	
	@Override
	@PostMapping("/create")
	public Result<UserDTO> create(@RequestBody UserDTO dto) throws URISyntaxException {
		return Result.createSuccess(userService.save(dto));
	}

	@Override
	@PutMapping("/update")
	public Result<UserDTO> update(@RequestBody UserDTO dto) throws URISyntaxException {
		UserDTO currDto = userService.findOne(dto.getId());
		if(currDto == null)
			return Result.createFail(dto).message(ResultMessage.NOT_FOUND_EXCEPTION).code(ResultCode.NOT_FOUND_EXCEPTION);
		else {
			dto.setId(currDto.getId());
			return Result.createSuccess(userService.save(dto));
		}
	}

	@Override
	@GetMapping("/get-all")
	public Result<Page<UserDTO>> getAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@GetMapping("/get-one")
	public Result<UserDTO> get(Long id) {
		return Result.createSuccess(userService.findOne(id));
	}

	@Override
	@DeleteMapping("/delete")
	public Result<Void> delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
