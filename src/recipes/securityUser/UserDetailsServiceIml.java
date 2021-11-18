package recipes.securityUser;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import recipes.persistence.UserRepository;

@Service
public class UserDetailsServiceIml implements UserDetailsService
{
	@Autowired
	UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{

		User user = userRepo.findUserByEmail(username).orElseThrow(() ->
				new UsernameNotFoundException("Not found: " + username));

		return new UserDetailsImpl(user);
	}

}
