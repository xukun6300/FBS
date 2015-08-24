package sg.com.fbs.model.system.security;

import java.util.Arrays;

import lombok.Getter;
import lombok.Setter;

public class UserCredentials {

	@Getter
	@Setter
	private char[] password;
	
	@Getter
	@Setter
	private char[] salt;
	
	public UserCredentials(){
		
	}
	
	public UserCredentials(char[] password, char[] salt){
		if(password!=null){
			this.password = Arrays.copyOf(password, password.length);
		}
		
		if(salt!=null){
			this.salt = Arrays.copyOf(salt, salt.length);
		}
	}
}
