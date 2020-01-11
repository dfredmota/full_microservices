package developersd3.api.users.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import developersd3.api.users.dto.UserDto;
import developersd3.api.users.entity.UserEntity;
import developersd3.api.users.repository.UsersRepository;

@Service
public class UserServiceImpl implements UserService {

	UsersRepository repository;
	
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public UserServiceImpl(UsersRepository repository,BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.repository = repository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@Override
	public UserDto createUser(UserDto dto) {
		
		dto.setUserId(UUID.randomUUID().toString());
		dto.setEncryptedPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
		
		ModelMapper mapper = new ModelMapper();
		
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserEntity userEntity = mapper.map(dto, UserEntity.class);
		
		repository.save(userEntity);
		
		UserDto returnValue = mapper.map(userEntity, UserDto.class);
		
		return returnValue;
	}
	
	

}
