package com.voldemort.mapper;

import org.springframework.stereotype.Component;

import com.voldemort.abstracts.VolMapper;
import com.voldemort.domain.User;
import com.voldemort.dto.UserDTO;

@Component
public class UserMapper extends VolMapper<User, UserDTO> {
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.voldemort.mapper.Mapper#toDTO(com.voldemort.domain.User)
	 */
	@Override
	public UserDTO toDTO(User user) {
		UserDTO dto = new UserDTO();
		dto.setUsername(user.getUsername());
		dto.setId(user.getId());
		dto.setPassword(user.getPassword());
		return dto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.voldemort.mapper.Mapper#toEtity(com.voldemort.dto.UserDTO)
	 */
	@Override
	public User toEtity(UserDTO userDTO) {
		if (userDTO == null)
			return null;
		else {
			User user = new User();
			user.setId(userDTO.getId());
			user.setUsername(userDTO.getUsername());
			user.setPassword(userDTO.getPassword());
			return user;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.voldemort.mapper.Mapper#entityFromId(java.lang.Integer)
	 */
	@Override
	public User entityFromId(Long id) {
		if (id == null)
			return null;
		else {
			User user = new User();
			user.setId(id);
			return user;
		}
	}
}
