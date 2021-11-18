package recipes.securityUser;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User
{
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int     id;

	@NotEmpty
	@NotBlank
	@Pattern(regexp = "\\w+@\\w+\\.\\w+", message = "Please send valid email!")
	@Column(name = "email")
	private String  email;

	@NotEmpty
	@NotBlank
	@Size(min = 8, message = "Please send valid password!")
	@Column(name = "password")
	private String  password;

//	@JsonIgnore
//	private String  role;

}
