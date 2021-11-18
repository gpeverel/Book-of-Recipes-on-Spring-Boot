package recipes.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import recipes.securityUser.User;

import java.util.Optional;

@Component
public interface UserRepository extends CrudRepository<User, Integer>
{

	Optional<User> findUserByEmail(String name);

	Boolean         existsByEmail(String email);

}
