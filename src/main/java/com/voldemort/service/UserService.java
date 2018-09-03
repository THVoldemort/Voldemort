package com.voldemort.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.voldemort.abstracts.VolService;
import com.voldemort.domain.User;
import com.voldemort.dto.UserDTO;
import com.voldemort.mapper.UserMapper;
import com.voldemort.repository.UserRepository;

@Service
public class UserService extends VolService<UserDTO> implements UserDetailsService{
	
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 BCryptPasswordEncoder encoder = passwordEncoder();
		User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}
