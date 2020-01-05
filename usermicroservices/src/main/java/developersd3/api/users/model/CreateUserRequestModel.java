package developersd3.api.users.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateUserRequestModel {
	
	@NotNull(message = "Primeiro Nome é Obrigatório!")
	@Size(min = 4 , message = "Primeiro nome deve ter no minimo 4 caracteres")
	private String firstName;
	
	@NotNull(message = "Último Nome é Obrigatório!")
	@Size(min = 4 , message = "Último nome deve ter no minimo 4 caracteres")
	private String lastName;
	
	@NotNull(message = "Senha é Obrigatória")
	@Size(min = 8 , max = 16 , message = "Senha deve ter no minimo 8 caracteres e no máximo 16")
	private String password;
	
	@NotNull(message = "Email é obrigatório!")
	@Email
	private String email;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
