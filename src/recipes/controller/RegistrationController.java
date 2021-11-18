package recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import recipes.persistence.UserRepository;
import recipes.securityUser.User;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Optional;

@RestController
public class RegistrationController
{
	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@PostMapping("api/register")
	@ResponseStatus(HttpStatus.OK)
	public void register(@Valid @NotNull @RequestBody User user)
	{
		if (userRepository.existsByEmail(user.getEmail()))
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
	}
}
