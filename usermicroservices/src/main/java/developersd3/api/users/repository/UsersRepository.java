package developersd3.api.users.repository;

import org.springframework.data.repository.CrudRepository;

import developersd3.api.users.entity.UserEntity;

public interface UsersRepository extends CrudRepository<UserEntity, Long> {
	
	UserEntity findByEmail(String email);
	

}
