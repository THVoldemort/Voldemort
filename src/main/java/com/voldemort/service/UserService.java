package com.voldemort.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.voldemort.abstracts.VolService;
import com.voldemort.domain.User;
import com.voldemort.dto.UserDTO;
import com.voldemort.mapper.UserMapper;
import com.voldemort.repository.UserRepository;

@Service
public class UserService extends VolService<UserDTO>{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public UserDTO save(UserDTO dto) {
		User user = userRepository.save(userMapper.toEtity(dto));
		return userMapper.toDTO(user);
	}

	@Override
	public Page<UserDTO> findAll(Pageable pageable) {
		
		return null;
	}

	@Override
	public UserDTO findOne(Long id) {
		Optional<User> optional = userRepository.findById(id);
		if(optional.isPresent())
			return userMapper.toDTO(optional.get());
		else
			return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}
}
