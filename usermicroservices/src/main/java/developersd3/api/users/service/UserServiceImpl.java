package developersd3.api.users.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import developersd3.api.users.dto.UserDto;
import developersd3.api.users.entity.UserEntity;
import developersd3.api.users.repository.UsersRepository;

@Service
public class UserServiceImpl implements UserService {

	UsersRepository repository;
	
	@Autowired
	public UserServiceImpl(UsersRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public UserDto createUser(UserDto dto) {
		
		dto.setUserId(UUID.randomUUID().toString());
		
		ModelMapper mapper = new ModelMapper();
		
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserEntity userEntity = mapper.map(dto, UserEntity.class);
		userEntity.setEncryptedPassword("test");
		
		repository.save(userEntity);
		
		return null;
	}
	
	

}
