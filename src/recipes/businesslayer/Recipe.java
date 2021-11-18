package recipes.businesslayer;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recipe")
public class Recipe
{
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int                 id;

	@JsonIgnore
	private int                 userId;

	@NotEmpty
	@NotBlank
	@Column(name = "name")
	private String              name;

	@NotEmpty
	@NotBlank
	@Column(name = "category")
	private String              category;

	@NotEmpty
	@NotBlank
	@Column(name = "description")
	private String              description;

	@Column(name = "date")
	private LocalDateTime       date;

	@NotNull
	@Size(min = 1)
	@Column(name = "ingredients")
	@ElementCollection
	private List<String>   ingredients;

	@NotNull
	@Size(min = 1)
	@Column(name = "directions")
	@ElementCollection
	private List<String> directions;
}
