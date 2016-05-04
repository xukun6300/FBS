package sg.com.fbs.model.system.security;

import java.util.Arrays;

import lombok.Getter;
import lombok.Setter;

public class UserCredentials {

	private char[] password;

	private char[] salt;
	
	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}

	public char[] getSalt() {
		return salt;
	}

	public void setSalt(char[] salt) {
		this.salt = salt;
	}

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
