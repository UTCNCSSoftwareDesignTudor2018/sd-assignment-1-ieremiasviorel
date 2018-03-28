package services.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements Validator {

	@Override
	public void validate(Object password) {
		
		Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$");
		Matcher matcher = pattern.matcher((String)password);

		if (!matcher.matches()) {
			throw new IllegalArgumentException("Invalid password format!");
		}
	}
}
